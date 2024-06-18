package com.farmigo.server.global.constant;

/**
 * @Description : 예약 상태 ENUM
 */
public enum ReserveStatus {
    CREATE_RESERVE(0, "견적서 생성"),
    REQUEST_RESERVE(1, "견적서 요청"),
    APPROVE_RESERVE(2, "예약 승인(예약 중)"),
    DENY_RESERVE(3, "예약 거절"),
    CANCEL_RESERVE(4, "예약 취소"),
    COMPLETE_RESERVE(5, "체험 완료"),
    ERROR_RESERVE(9, "에러");

    private final int value;
    private final String name;

    ReserveStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
