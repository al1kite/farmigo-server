package com.farmigo.server.domain.farm.repository;

import com.farmigo.server.domain.farm.model.vo.Search;
import com.farmigo.server.domain.profile.model.vo.MinMaxPriceInfo;
import com.farmigo.server.global.constant.MongoField;
import com.farmigo.server.global.model.mongo.master.MST_ACTIVITY;
import com.farmigo.server.global.repository.master.MasterMongoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description : 체험 정보
 */
@Repository
public class MstActivityMongoRepository extends MasterMongoRepository<MST_ACTIVITY> {

    @Override
    protected Class<MST_ACTIVITY> getDataType() {
        return MST_ACTIVITY.class;
    }

    public MST_ACTIVITY findByActivityId(String activityId) {
        Query query = Query.query(Criteria.where(MongoField._ID).is(activityId));
        return super.findOne(query);
    }

    public List<MST_ACTIVITY> findByActivityByEtAndLimit(long et, int limit) {
        Query query = Query.query(Criteria.where(MongoField.END_TIME).lte(et))
                .with(Sort.by(Sort.Direction.DESC, MongoField.END_TIME))
                .limit(limit);

        return super.find(query);
    }

    public List<MST_ACTIVITY> searchByOffset(Search search, String offsetId, int limit) {
        Criteria criteria = Criteria.where(MongoField._ID).gte(offsetId);

        if (search.getDate().getStart() != 0) {
            criteria.and(MongoField.START_TIME).gte(search.getDate().getStart());
        }
        if (search.getDate().getEnd() != 0) {
            criteria.and(MongoField.END_TIME).lte(search.getDate().getEnd());
        }
        if (!search.getCity().isEmpty()) {
            criteria.and(MongoField.STATE).in(search.getState());
        }
        if (!search.getCity().isEmpty()) {
            criteria.and(MongoField.CITY).in(search.getCity());
        }
        if (search.getBudget().getMin() != 0) {
            criteria.and(MongoField.PRICE_INFO + "." + MongoField.TOTAL_PRICE).gte(search.getBudget().getMin());
        }
        if (search.getBudget().getMax() != 0) {
            criteria.and(MongoField.PRICE_INFO + "." + MongoField.TOTAL_PRICE).lte(search.getBudget().getMax());
        }

        Query query = Query.query(criteria)
                .with(Sort.by(Sort.Direction.DESC, MongoField.END_TIME))
                .limit(limit);

        return super.find(query);
    }

    public List<MST_ACTIVITY> findByFarmId(String farmId) {
        Query query = Query.query(Criteria.where(MongoField.FARM_ID).is(farmId));
        return super.find(query);
    }

    public MinMaxPriceInfo getMaxAndMinPrice(){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group().max(MongoField.TOTAL_PRICE_INFO).as(MongoField.MAX_PRICE)
                        .min(MongoField.TOTAL_PRICE_INFO).as(MongoField.MIN_PRICE)
        );

        AggregationResults<MinMaxPriceInfo> result = super.aggregate(aggregation, MinMaxPriceInfo.class);
       return result.getUniqueMappedResult();
    }

    public List<MST_ACTIVITY> findByActivityIdList(List<String> activityIds) {
        Query query = Query.query(Criteria.where(MongoField._ID).in(activityIds));
        return super.find(query);
    }
}
