package com.farmigo.server.global.config.mongo.user;

import com.farmigo.server.global.config.mongo.MongoConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description :
 */
@Configuration
@ConfigurationProperties(prefix = "datastore.mongodb.user")
public class UserMongoConfig extends MongoConfig {
}
