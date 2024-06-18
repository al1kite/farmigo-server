package com.farmigo.server.domain.auth.model.enumeration;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("0", "ROLE_ADMIN"),// BackOffice 전체 Admin 관리 (개발팀)
    FARM("100", "ROLE_FARM"),
    YOUTH("200", "ROLE_YOUTH");

    private final String value;
    private final String name;

    Role(String value, String name) {
        this.value = value;
        this.name = name;
    }

}
