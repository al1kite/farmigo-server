package com.farmigo.server.global.model.request;

import com.farmigo.server.global.constant.AppId;
import com.farmigo.server.global.model.common.user.UserAgent;
import com.farmigo.server.global.model.common.user.UserContext;
import com.farmigo.server.global.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Description : Request Root
 */

@Getter
@Setter
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class Request {
    @JsonIgnore
    @Hidden
    private String accessToken;

    @JsonIgnore
    @Hidden
    private UserContext userContext;

    @JsonIgnore
    private UserAgent userAgent;

    @JsonIgnore
    @Hidden
    private String remoteIp;

    @JsonIgnore
    @Hidden
    private boolean refreshUserInfo;

    @Hidden
    private long txTime;
    @Hidden
    private LocalDateTime txTimeDate;

    @Hidden
    private boolean isLoginNotRequired = false;

    public long getTxTime() {
        if (this.txTime == 0L) {
            this.txTime = DateUtil.getCurrentEpochMilli();
            this.txTimeDate = DateUtil.epochToDate(txTime);
        }
        return txTime;
    }

    public void setTxTime(long time) {
        this.txTime = time;
        this.txTimeDate = DateUtil.epochToDate(txTime);
    }

    public LocalDateTime getTxTimeDate() {
        if (this.txTime == 0L) {
            this.getTxTime();
        }
        return txTimeDate;
    }

    public static Request of() {
        Request ctx = new Request();
        ctx.getTxTime();

        return ctx;
    }

    public void parseAccessTokenFromParameter(String token) {
        log.debug("[parseAccessTokenFromParameter] request token : {}", token);
        this.accessToken = token;
    }

    @Hidden
    public boolean isLoginUser() {
        return getUserContext() != null && getUserContext().getId() != 0;
    }

    @Hidden
    public long getId() {
        return isLoginUser() ? getUserContext().getId() : 0;
    }

    @Hidden
    public String getRemoteIp() {
        if (userAgent == null) {
            return "";
        }
        return userAgent.getIpv4();
    }

    //    UserAgent 처리시 사용되는 코드.
    @Hidden
    public String getIpv4() {
        if (userAgent == null) {
            return "";
        }
        return userAgent.getIpv4();
    }

    @Hidden
    public boolean isIos() {
        return getAppId() == AppId.iOS.getValue();
    }

    public String getClientVersion() {
        if (userAgent == null) {
            return "";
        }
        return userAgent.getClientVersion();
    }

    public int getAppId() {
        if (userAgent == null) {
            return 0;
        }
        return userAgent.getAppid();
    }

    public String getClientName() {
        if (userAgent == null) {
            return "";
        }
        return userAgent.getClientName();
    }

    public String getDevice() {
        if (userAgent == null) {
            return "";
        }
        return userAgent.getDevice();
    }

    public String getOs() {
        if (userAgent == null) {
            return "";
        }
        return userAgent.getOsType();
    }

    public String toString() {
        long id = 0;

        if (userContext != null) {
            id = userContext.getId();
        }

        return String.format("[RequestContext] id: %d, txTime: %d", id, txTime);
    }
}
