package com.farmigo.server.global.config.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.management.JMXConnectionPoolListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Description :
 */

@Slf4j
public abstract class AbstractMongoConfiguration extends AbstractMongoClientConfiguration {

    protected abstract MongoConfig getMongoConfig();

    @Override
    protected String getDatabaseName() {
        return this.getMongoConfig().getDatabase();
    }

    @Override
    public MongoClient mongoClient() {
        MongoConfig mongoProperty = this.getMongoConfig();

        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(applyAccountInfoToConnectUri(mongoProperty)))
                .applyToConnectionPoolSettings(setting -> {
                    setting.addConnectionPoolListener(new JMXConnectionPoolListener())
                            .minSize(mongoProperty.getMinConnectionsPerHost())
                            .maxSize(mongoProperty.getConnectionsPerHost())
                            .maxWaitTime(mongoProperty.getMaxWaitTime(), TimeUnit.MILLISECONDS);
                }).applyToClusterSettings(setting -> {
                    setting.serverSelectionTimeout(mongoProperty.getServerSelectionTimeout(), TimeUnit.MILLISECONDS);
                }).applyToSocketSettings(setting -> {
                    setting.connectTimeout(mongoProperty.getConnectTimeout(), TimeUnit.MILLISECONDS)
                            .readTimeout(mongoProperty.getSocketTimeout(), TimeUnit.MILLISECONDS);
                })
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    private String applyAccountInfoToConnectUri(MongoConfig mongoProperty) {
        if (!StringUtils.hasText(mongoProperty.getUri())) {
            return mongoProperty.getUri();
        }

        String appliedUri = mongoProperty.getUri();
        if (StringUtils.hasText(mongoProperty.getUsername())) {
            appliedUri = mongoProperty.getUri().replace("_MONGODB_USER_", mongoProperty.getUsername());
        }
        if (StringUtils.hasText(mongoProperty.getProperPassword())) {
            appliedUri = appliedUri.replace("_MONGODB_PW_", mongoProperty.getProperPassword());
        }
        return appliedUri;
    }

    @Override
    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory databaseFactory, MongoCustomConversions customConversions,
                                                       MongoMappingContext mappingContext) {
        MappingMongoConverter mappingMongoConverter = super.mappingMongoConverter(databaseFactory, customConversions, mappingContext);
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null)); // document에 _class 필드 추가 안되도록

        return mappingMongoConverter;
    }
}
