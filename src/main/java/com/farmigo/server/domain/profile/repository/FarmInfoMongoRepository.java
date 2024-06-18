package com.farmigo.server.domain.profile.repository;

import com.farmigo.server.global.model.mongo.master.MST_FARM;
import com.farmigo.server.global.repository.user.UserMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FarmInfoMongoRepository extends UserMongoRepository<MST_FARM> {
    @Override
    protected Class<MST_FARM> getDataType() {
        return MST_FARM.class;
    }
}
