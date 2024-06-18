package com.farmigo.server.domain.profile.repository;

import com.farmigo.server.domain.profile.model.enumeration.ReserveType;
import com.farmigo.server.domain.profile.model.request.ReserveSearchRequest;
import com.farmigo.server.global.constant.MongoField;
import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_RESERVE;
import com.farmigo.server.global.repository.user.UserMongoRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReserveRepository extends UserMongoRepository<USER_ACTIVITY_RESERVE> {
    @Override
    protected Class<USER_ACTIVITY_RESERVE> getDataType() {
        return USER_ACTIVITY_RESERVE.class;
    }

    private Criteria createQuery(ReserveType reserveType) {
        LocalDateTime txTime = LocalDateTime.now();

        return switch (reserveType) {
            case UC -> Criteria.where(MongoField.START_TIME).gte(txTime);
            case PA -> Criteria.where(MongoField.END_TIME).lt(txTime);
            case PC -> Criteria.where(MongoField.START_TIME).lt(txTime)
                    .and(MongoField.END_TIME).gte(txTime);
            default -> new Criteria();
        };
    }

    public Map<ReserveType, Long> findReserveCountByType(String farmId) {
        Map<ReserveType, Long> reserveTypeMap = new HashMap<>();

        Criteria farmCriteria = Criteria.where(MongoField.FARM_ID).is(farmId);

        for (ReserveType type : ReserveType.values()) {
            reserveTypeMap.put(type, super.count(new Query().addCriteria(farmCriteria)
                    .addCriteria(this.createQuery(type))));
        }

        return reserveTypeMap;
    }


    public List<USER_ACTIVITY_RESERVE> findReserveByType(ReserveSearchRequest reserveSearchRequest, String userId) {
        // 예약 형태(진행 중, 지난, 예정)에 따른 쿼리 추가
        Query query = new Query();
        query.addCriteria(Criteria.where(MongoField.USER_ID).is(userId));

        query.addCriteria(this.createQuery(reserveSearchRequest.getReserveType()));

        query.addCriteria(Criteria.where(MongoField._ID).gte(reserveSearchRequest.getOffset()));
        query.limit(reserveSearchRequest.getLimit());

        return super.find(query);
    }

    public List<USER_ACTIVITY_RESERVE> findReserveByTypeAndActivity(ReserveSearchRequest reserveSearchRequest, String farmId, String offset, int limit) {
        // 예약 형태(진행 중, 지난, 예정)에 따른 쿼리 추가
        Query query = new Query();
        query.addCriteria(Criteria.where(MongoField.FARM_ID).is(farmId));

        query.addCriteria(this.createQuery(reserveSearchRequest.getReserveType()));

        query.addCriteria(Criteria.where(MongoField._ID).gte(offset));
        query.limit(limit);

        return super.find(query);
    }
}
