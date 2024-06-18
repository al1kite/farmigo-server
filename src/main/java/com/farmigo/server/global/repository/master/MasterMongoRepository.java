package com.farmigo.server.global.repository.master;

import com.farmigo.server.global.repository.AbstractMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @Description : Master Mongo Repository
 */
public abstract class MasterMongoRepository<T> extends AbstractMongoRepository<T> {

    @Autowired
    @Qualifier("masterMongoTemplate")
    private MongoTemplate masterMongoTemplate;

    @Override
    public MongoTemplate getMongoTemplate() {
        return masterMongoTemplate;
    }
}
