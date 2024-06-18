package com.farmigo.server.domain.farm.repository;

import com.farmigo.server.global.constant.MongoField;
import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_REVIEW;
import com.farmigo.server.global.repository.user.UserMongoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description :
 */
@Repository
public class UserActivityReviewMongoRepository extends UserMongoRepository<USER_ACTIVITY_REVIEW> {

    @Override
    protected Class<USER_ACTIVITY_REVIEW> getDataType() {
        return USER_ACTIVITY_REVIEW.class;
    }

    public List<USER_ACTIVITY_REVIEW> findByActivityIdAndOffset(String activityId, String offsetId, int limit) {
        Query query = Query.query(Criteria.where(MongoField.ACTIVITY_ID).is(activityId).and(MongoField._ID).gte(offsetId))
                .with(Sort.by(Sort.Direction.DESC, MongoField._ID))
                .limit(limit);
        return super.find(query);
    }
}
