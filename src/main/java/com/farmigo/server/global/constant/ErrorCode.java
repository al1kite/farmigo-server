package com.farmigo.server.global.constant;

import lombok.Getter;

/**
 * @Description : Server 모든 Error Code 정의
 */
@Getter
public enum ErrorCode {
    ERR_UNKNOWN(-1, "Unknown(-1)"),
    SUCCESS(200, "Success !!(200)"),
    DELETE_FAIL(501,"Delete Resource Failed(501)"),
    UPDATE_FAIL(502,"Update Resource Failed(502)"),
    // RESERVE
    RESERVE_CREATE_FAIL(1000, "Reserve Create Fail(1000)"),
    RESERVE_REQUEST_FAIL(1001, "Reserve Request Fail(1001)"),
    RESOURCE_NOT_FOUND(404,"Resource Not Found(404)"),
    ;
    private final int value;
    private final String description;

    ErrorCode(int errorValue, String description) {
        this.value = errorValue;
        this.description = description;
    }

    public String getErrorMessage() {
        return getDescription();
    }
}
