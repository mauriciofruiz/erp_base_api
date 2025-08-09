package com.sicon.tba.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum PaisEnum {

    ECUADOR("593", "ECUADOR");

    private String id;
    private String description;

    PaisEnum(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public static String getDescriptionById(String id) {
        for (PaisEnum e : values()) {
            if (e.id.equals(id)) {
                return e.description;
            }
        }
        return null; // or throw an exception
    }
}
