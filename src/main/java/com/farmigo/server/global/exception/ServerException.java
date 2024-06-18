package com.farmigo.server.global.exception;

import com.farmigo.server.global.constant.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description : 예외 처리
 */
@Getter
@Setter
@NoArgsConstructor
public class ServerException extends RuntimeException {
    private int errorCode;
    private String msg;

    public ServerException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode.getValue();
        this.msg = errorCode.getDescription();
    }

    public ServerException(ErrorCode errorCode, String errorMsg) {
        super(errorCode.getDescription() + " " + errorMsg);
        this.errorCode = errorCode.getValue();
        this.msg = errorMsg;
    }
}
