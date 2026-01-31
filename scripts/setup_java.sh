#!/bin/bash
# ===========================================
# Java Environment Setup Helper
# ===========================================
# This script helps set up JAVA_HOME for the project

# Colors for output
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}Checking Java installation...${NC}"

# Try to find Java 21
JAVA_21_PATH=""
POSSIBLE_PATHS=(
    "/usr/lib/jvm/java-21-openjdk-amd64"
    "/usr/lib/jvm/java-21-openjdk-$(uname -m)"
    "/usr/lib/jvm/java-21-oracle"
    "/usr/lib/jvm/java-21"
)

for path in "${POSSIBLE_PATHS[@]}"; do
    if [ -d "$path" ] && [ -f "$path/bin/java" ]; then
        JAVA_21_PATH="$path"
        break
    fi
done

# If not found, try update-alternatives
if [ -z "$JAVA_21_PATH" ]; then
    JAVA_21_PATH=$(update-alternatives --list java 2>/dev/null | grep -i "java-21" | sed 's|/bin/java||' | head -n 1)
fi

# If still not found, try to find any java-21 directory
if [ -z "$JAVA_21_PATH" ]; then
    JAVA_21_PATH=$(find /usr/lib/jvm -maxdepth 1 -type d -name "*java-21*" 2>/dev/null | head -n 1)
fi

if [ -z "$JAVA_21_PATH" ]; then
    echo -e "${RED}Java 21 not found!${NC}"
    echo ""
    echo "Please install Java 21 with:"
    echo "  sudo apt-get update"
    echo "  sudo apt-get install -y openjdk-21-jdk"
    echo ""
    echo "After installation, run this script again to set up JAVA_HOME."
    exit 1
fi

# Verify it's Java 21
JAVA_VERSION=$("$JAVA_21_PATH/bin/java" -version 2>&1 | head -n 1)
if ! echo "$JAVA_VERSION" | grep -q "21"; then
    echo -e "${YELLOW}Warning: Found Java at $JAVA_21_PATH but version doesn't appear to be 21${NC}"
    echo "Version info: $JAVA_VERSION"
    read -p "Continue anyway? (y/n) " -n 1 -r
    echo
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        exit 1
    fi
fi

echo -e "${GREEN}Found Java 21 at: $JAVA_21_PATH${NC}"
echo ""

# Set JAVA_HOME for current session
export JAVA_HOME="$JAVA_21_PATH"
export PATH="$JAVA_HOME/bin:$PATH"

echo -e "${GREEN}JAVA_HOME set to: $JAVA_HOME${NC}"
echo -e "${GREEN}Java version:${NC}"
"$JAVA_HOME/bin/java" -version

echo ""
echo -e "${YELLOW}To make this permanent, add these lines to your ~/.bashrc or ~/.zshrc:${NC}"
echo "export JAVA_HOME=\"$JAVA_21_PATH\""
echo "export PATH=\"\$JAVA_HOME/bin:\$PATH\""
echo ""
echo -e "${BLUE}Or run this command to add it now:${NC}"
echo "echo 'export JAVA_HOME=\"$JAVA_21_PATH\"' >> ~/.bashrc"
echo "echo 'export PATH=\"\$JAVA_HOME/bin:\$PATH\"' >> ~/.bashrc"
echo ""
echo -e "${GREEN}For this session, JAVA_HOME is now set. You can run: ./gradlew bootRun${NC}"
