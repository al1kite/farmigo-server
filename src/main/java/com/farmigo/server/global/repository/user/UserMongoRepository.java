package com.farmigo.server.global.repository.user;

import com.farmigo.server.global.repository.AbstractMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @Description :
 */
public abstract class UserMongoRepository<T> extends AbstractMongoRepository<T> {

    @Autowired
    @Qualifier("userMongoTemplate")
    private MongoTemplate userMongoTemplate;

    @Override
    public MongoTemplate getMongoTemplate() {
        return userMongoTemplate;
    }
}
