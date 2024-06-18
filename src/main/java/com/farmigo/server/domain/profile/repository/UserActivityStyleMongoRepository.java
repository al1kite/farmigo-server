package com.farmigo.server.domain.profile.repository;

import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_STYLE;
import com.farmigo.server.global.repository.user.UserMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserActivityStyleMongoRepository extends UserMongoRepository<USER_ACTIVITY_STYLE> {
    @Override
    protected Class<USER_ACTIVITY_STYLE> getDataType() {
        return USER_ACTIVITY_STYLE.class;
    }

}
