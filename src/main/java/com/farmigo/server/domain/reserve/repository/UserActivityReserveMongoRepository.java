package com.farmigo.server.domain.reserve.repository;

import com.farmigo.server.domain.reserve.model.vo.FarmReserveStats;
import com.farmigo.server.global.constant.MongoField;
import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_RESERVE;
import com.farmigo.server.global.repository.user.UserMongoRepository;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @Description :
 */
@Repository
public class UserActivityReserveMongoRepository extends UserMongoRepository<USER_ACTIVITY_RESERVE> {

    @Override
    protected Class<USER_ACTIVITY_RESERVE> getDataType() {
        return USER_ACTIVITY_RESERVE.class;
    }

    public USER_ACTIVITY_RESERVE findByUserIdAndActivityId(long userId, String activityId) {
        Query query = Query.query(where(MongoField.USER_ID).is(userId).and(MongoField.ACTIVITY_ID).is(activityId));
        return super.findOne(query);
    }

    public List<FarmReserveStats> aggregateByMonth(String farmId, LocalDateTime startMonth, LocalDateTime endMonth) {
        MatchOperation matchStage = Aggregation.match(
                Criteria.where(MongoField.FARM_ID).is(farmId)
                        .and(MongoField.START_TIME).gte(startMonth).lt(endMonth)
        );
        ProjectionOperation projectStage = Aggregation.project(MongoField.START_TIME)
                .andExpression(MongoField.START_TIME).dateAsFormattedString("%Y%m").as("month");

        GroupOperation groupStage = Aggregation.group("month").count().as("count");

        Aggregation aggregation = Aggregation.newAggregation(matchStage, projectStage, groupStage);

        return super.aggregate(aggregation, FarmReserveStats.class).getMappedResults();
    }

    public List<USER_ACTIVITY_RESERVE> findByFarmIdAndStatus(String farmId, int status, String offset, int limit) {
        Query query = Query.query(where(MongoField.FARM_ID).is(farmId)
                .and(MongoField.STATUS).is(status)
                .and(MongoField._ID).gte(offset)
        );
        query.limit(limit);
        return super.find(query);
    }

    public USER_ACTIVITY_RESERVE findById(String reserveId) {
        Query query = Query.query(where(MongoField._ID).is(reserveId));
        return super.findOne(query);
    }

    public boolean updateStatus(String reserveId, int status) {
        Query query = Query.query(where(MongoField._ID).is(reserveId));
        Update update = new Update().set(MongoField.STATUS, status);
        return  super.updateFirst(query, update).getModifiedCount() > 0;
    }
}
