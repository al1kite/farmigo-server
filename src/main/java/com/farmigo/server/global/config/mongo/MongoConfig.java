package com.farmigo.server.global.config.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;

/**
 * @Description :
 */
@Getter
@Setter
public class MongoConfig extends MongoProperties {
    private String encpassword;
    private String database;
    private int connectionsPerHost;
    private int minConnectionsPerHost;
    private int connectTimeout;
    private Long maxWaitTime;
    private int socketTimeout;
    private String readPreference;
    private String writeConcern;
    private Long serverSelectionTimeout;
    private Long maxConnectionIdleTime;
    public String getProperPassword() {
        return new String(super.getPassword());
    }
}
