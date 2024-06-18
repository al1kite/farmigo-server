package com.farmigo.server.domain.chat.repository;

import com.farmigo.server.global.constant.MongoField;
import com.farmigo.server.global.model.mongo.user.USER_CHAT_MESSAGE;
import com.farmigo.server.global.repository.user.UserMongoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @Description :
 */
@Repository
public class UserChatMessageMongoRepository extends UserMongoRepository<USER_CHAT_MESSAGE> {

    @Override
    protected Class<USER_CHAT_MESSAGE> getDataType() {
        return USER_CHAT_MESSAGE.class;
    }

    public List<USER_CHAT_MESSAGE> findByChatRoomIdWithOffset(String chatRoomId, String offset, int limit) {
        Query query = Query.query(where(MongoField.CHAT_ROOM_ID).is(chatRoomId).and(MongoField._ID).lt(Long.parseLong(offset)))
                .with(Sort.by(Sort.Direction.DESC, MongoField._ID))
                .limit(limit);

        return super.find(query);
    }

    public long getLastSequence() {
        Query query = new Query().limit(1).with(Sort.by(Sort.Direction.DESC, MongoField._ID));
        return super.findOne(query).getSeq();
    }
}
