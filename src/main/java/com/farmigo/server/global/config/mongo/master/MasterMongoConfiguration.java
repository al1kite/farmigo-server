package com.farmigo.server.global.config.mongo.master;

import com.farmigo.server.global.config.mongo.AbstractMongoConfiguration;
import com.farmigo.server.global.config.mongo.MongoConfig;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static com.mongodb.WriteConcern.MAJORITY;

/**
 * @Description : MasterDB Configuration
 */
@Configuration
@EnableMongoRepositories
public class MasterMongoConfiguration extends AbstractMongoConfiguration {

    private final MongoConfig masterMongoConfig;

    public MasterMongoConfiguration(@Qualifier("masterMongoConfig") MasterMongoConfig masterMongoConfig) {
        this.masterMongoConfig = masterMongoConfig;
    }

    @Override
    public MongoConfig getMongoConfig() {
        return masterMongoConfig;
    }

    @Bean(name = "masterMongoTransactionManager")
    public MongoTransactionManager transactionManager(@Qualifier("masterMongoDbFactory") MongoDatabaseFactory dbFactory) {
        TransactionOptions txnOptions = TransactionOptions.builder()
                .readPreference(ReadPreference.primary())
                .readConcern(ReadConcern.LOCAL)
                .writeConcern(MAJORITY)
                .build();

        return new MongoTransactionManager(dbFactory, txnOptions);
    }

    @Primary
    @Bean(name = "masterMongoDbFactory")
    public MongoDatabaseFactory masterMongoDbFactory() {
        return super.mongoDbFactory();
    }

    @Primary
    @Bean(name = "masterMongoTemplate")
    public MongoTemplate masterMongoTemplate(@Qualifier("masterMongoDbFactory") MongoDatabaseFactory mongoDbFactory,
                                       MappingMongoConverter converter) {
        return super.mongoTemplate(mongoDbFactory, converter);
    }
}
