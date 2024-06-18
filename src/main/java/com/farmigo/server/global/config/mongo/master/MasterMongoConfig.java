package com.farmigo.server.global.config.mongo.master;

import com.farmigo.server.global.config.mongo.MongoConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Description :
 */
@Primary
@Configuration
@ConfigurationProperties(prefix = "datastore.mongodb.master")
public class MasterMongoConfig extends MongoConfig {
}
