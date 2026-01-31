#!/usr/bin/env python3
"""
Remove Tba, Con, Vts, Adq, Rrh, Trb, Inv prefixes from class names and file names.
Skips names that would conflict with existing unprefixed classes.
"""
import os
import re
from pathlib import Path

# Names to NOT rename (would conflict with existing classes)
SKIP_UNPREFIXED = {"Rol", "RolUsuario", "Proveedor", "Sucursal", "Caja"}

PREFIXES = ["Tba", "Con", "Vts", "Adq", "Rrh", "Trb", "Inv"]

SRC_ROOT = Path(__file__).resolve().parent.parent / "src" / "main"
JAVA_DIR = SRC_ROOT / "java" / "erp" / "base" / "api"
KOTLIN_DIR = SRC_ROOT / "kotlin" / "erp" / "base" / "api"


def find_prefixed_identifiers():
    """Find all prefixed identifiers in the codebase (whole-word matches)."""
    seen = set()
    for src_dir in [JAVA_DIR, KOTLIN_DIR]:
        if not src_dir.exists():
            continue
        for path in src_dir.rglob("*.java"):
            _collect_from_file(path, seen)
        for path in src_dir.rglob("*.kt"):
            _collect_from_file(path, seen)
    return seen


def _collect_from_file(path, seen):
    try:
        text = path.read_text(encoding="utf-8", errors="replace")
    except Exception:
        return
    for prefix in PREFIXES:
        # Match prefix + PascalCase rest (whole word)
        for m in re.finditer(rf"\b{re.escape(prefix)}([A-Z][A-Za-z0-9]*)\b", text):
            full = m.group(0)
            rest = m.group(1)
            if rest not in SKIP_UNPREFIXED:
                seen.add((full, rest))


def build_replacements():
    """Build (old, new) pairs, longest first so we don't replace substrings wrongly."""
    collected = set()
    for src_dir in [JAVA_DIR, KOTLIN_DIR]:
        if not src_dir.exists():
            continue
        for path in src_dir.rglob("*.java"):
            _collect_from_file(path, collected)
        for path in src_dir.rglob("*.kt"):
            _collect_from_file(path, collected)
    # (full, rest) -> we replace full with rest. Sort by len(full) desc so "TbaUsuarioService" before "TbaUsuario"
    out = sorted([(full, rest) for full, rest in collected], key=lambda x: -len(x[0]))
    return out


def replace_in_files(replacements):
    """Apply replacements in all Java/Kotlin files. Longest first."""
    for src_dir in [JAVA_DIR, KOTLIN_DIR]:
        if not src_dir.exists():
            continue
        for path in list(src_dir.rglob("*.java")) + list(src_dir.rglob("*.kt")):
            try:
                text = path.read_text(encoding="utf-8", errors="replace")
            except Exception:
                continue
            original = text
            for old, new in replacements:
                # Whole word only
                text = re.sub(rf"\b{re.escape(old)}\b", new, text)
            if text != original:
                path.write_text(text, encoding="utf-8")
                print(f"Updated: {path.relative_to(SRC_ROOT)}")


def list_prefixed_files():
    """List (path, new_name) for files whose stem starts with a prefix."""
    renames = []
    for src_dir in [JAVA_DIR, KOTLIN_DIR]:
        if not src_dir.exists():
            continue
        for path in src_dir.rglob("*"):
            if not path.is_file():
                continue
            stem = path.stem
            for prefix in PREFIXES:
                if stem.startswith(prefix) and len(stem) > len(prefix) and stem[len(prefix)].isupper():
                    rest = stem[len(prefix):]
                    if rest in SKIP_UNPREFIXED:
                        continue
                    new_name = path.parent / (rest + path.suffix)
                    if new_name != path and not new_name.exists():
                        renames.append((path, new_name))
                    break
    return renames


def main():
    print("Building replacement pairs (content)...")
    replacements = build_replacements()
    # Deduplicate by old name, keeping first (longest) - actually we have (full, rest) and we want to replace each full with rest once
    by_old = {}
    for old, new in replacements:
        if old not in by_old:
            by_old[old] = new
    replacements = [(k, by_old[k]) for k in sorted(by_old.keys(), key=lambda x: -len(x))]
    print(f"Applying {len(replacements)} replacements in files...")
    replace_in_files(replacements)

    print("Listing file renames...")
    renames = list_prefixed_files()
    # If target exists (e.g. Rol.java exists, we're not renaming TbaRol.java), skip
    renames = [(p, q) for p, q in renames if not q.exists() or p == q]
    print(f"Renaming {len(renames)} files...")
    for old_path, new_path in renames:
        try:
            old_path.rename(new_path)
            print(f"  {old_path.name} -> {new_path.name}")
        except Exception as e:
            print(f"  Skip {old_path}: {e}")


if __name__ == "__main__":
    main()
