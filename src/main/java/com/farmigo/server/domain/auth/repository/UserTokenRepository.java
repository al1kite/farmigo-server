package com.farmigo.server.domain.auth.repository;

import com.farmigo.server.global.model.mongo.user.USER_TOKEN;
import com.farmigo.server.global.repository.user.UserMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserTokenRepository extends UserMongoRepository<USER_TOKEN> {
    @Override
    protected Class<USER_TOKEN> getDataType() {
        return USER_TOKEN.class;
    }
}
