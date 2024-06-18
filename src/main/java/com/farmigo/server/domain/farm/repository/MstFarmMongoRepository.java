package com.farmigo.server.domain.farm.repository;

import com.farmigo.server.global.constant.MongoField;
import com.farmigo.server.global.model.mongo.master.MST_FARM;
import com.farmigo.server.global.repository.master.MasterMongoRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description :
 */
@Repository
public class MstFarmMongoRepository extends MasterMongoRepository<MST_FARM> {

    @Override
    protected Class<MST_FARM> getDataType() {
        return MST_FARM.class;
    }

    public MST_FARM findByFarmId(String farmId) {
        Query query = Query.query(Criteria.where(MongoField._ID).is(farmId));
        return super.findOne(query);
    }

    public MST_FARM findByFarmerId(String userId) {
        Query query = Query.query(Criteria.where(MongoField.FARMER_ID).is(userId));
        return super.findOne(query);
    }

    public List<MST_FARM> findByFarmIdList(List<String> farmIdList) {
        Query query = Query.query(Criteria.where(MongoField._ID).in(farmIdList));
        return  super.find(query);
    }
}
