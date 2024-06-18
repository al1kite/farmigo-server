package com.farmigo.server.domain.auth.repository;

import com.farmigo.server.domain.profile.model.request.ProfileRequest;
import com.farmigo.server.global.constant.MongoField;
import com.farmigo.server.global.model.mongo.user.USER_INFO;
import com.farmigo.server.global.repository.user.UserMongoRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import static com.farmigo.server.global.constant.MongoField.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class UserRepository extends UserMongoRepository<USER_INFO> {
    public USER_INFO findUserByEmail(String email){
        Query query = Query.query(Criteria.where(EMAIL).is(email));
        return super.findOne(query);
    }

    public boolean updateProfile(String userId, ProfileRequest profileRequest){
        Query query = Query.query(where(MongoField._ID).is(userId));
        Update update = new Update().set(PROFILE_URL, profileRequest.getProfileUrl())
                .set(NAME, profileRequest.getName())
                .set(PHONE_NUMBER, profileRequest.getPhoneNumber());
        UpdateResult result =  super.updateFirst(query, update);
        return result.getModifiedCount() > 0;
    }

    /**
     * deprecated
     */
    public USER_INFO findUserByUserId(String userId){
        Query query = Query.query(Criteria.where(_ID).is(userId));
        return super.findOne(query);
    }

    @Override
    protected Class<USER_INFO> getDataType() {
        return USER_INFO.class;
    }
}
