package com.farmigo.server.domain.scrap.repository;

import com.farmigo.server.global.constant.MongoField;
import com.farmigo.server.global.model.mongo.user.USER_SCRAP;
import com.farmigo.server.global.repository.user.UserMongoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class UserScrapMongoRepository extends UserMongoRepository<USER_SCRAP> {
    @Override
    protected Class<USER_SCRAP> getDataType() {
        return USER_SCRAP.class;
    }

    public List<USER_SCRAP> searchByOffset(String userId, String offsetId, int limit) {
        Query query = Query.query(Criteria.where(MongoField.USER_ID).is(userId).and(MongoField._ID).gte(offsetId))
                .with(Sort.by(Sort.Direction.DESC, MongoField.CREATE_TIME))
                .limit(limit);

        return super.find(query);
    }

    public boolean delete(String activityId, String userId) {
        Query query = Query.query(where(MongoField.ACTIVITY_ID).is(activityId))
                .addCriteria(Criteria.where(MongoField.USER_ID).is(userId));
        return super.remove(query).getDeletedCount() > 0;
    }

}
