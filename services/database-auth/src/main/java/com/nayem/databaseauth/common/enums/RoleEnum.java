package com.nayem.databaseauth.common.enums;

import com.nayem.databaseauth.common.constants.Msg;
import com.nayem.databaseauth.common.exceptions.EnumNotFoundException;
import com.nayem.databaseauth.common.utilities.ExceptionUtil;

public enum RoleEnum {
    ROLE_ADMIN(1, "Role Admin", "Admin", "ROLE_ADMIN"),
    ROLE_USER(2, "Role User", "User", "ROLE_USER"),
    ROLE_MANAGER(3, "Role Manager", "Manager", "ROLE_MANAGER");

    private Integer value;
    private String description;
    private String label;
    private String name;

    RoleEnum(Integer value, String description, String label, String name) {
        this.value = value;
        this.description = description;
        this.label = label;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RoleEnum getByLabel(String label) throws EnumNotFoundException {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.label.equalsIgnoreCase(label))
                return roleEnum;
        }
        throw ExceptionUtil.getEnumNotFoundException(Msg.Enum.ROLE_ENUM);
    }
}
