package com.farmigo.server.domain.chat.repository;

import com.farmigo.server.global.constant.MongoField;
import com.farmigo.server.global.model.mongo.user.USER_CHAT_ROOM;
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
public class UserChatRoomMongoRepository extends UserMongoRepository<USER_CHAT_ROOM> {

    @Override
    protected Class<USER_CHAT_ROOM> getDataType() {
        return USER_CHAT_ROOM.class;
    }

    public USER_CHAT_ROOM findByChatRoomId(String chatRoomId) {
        Query query = Query.query(where(MongoField.CHAT_ROOM_ID).is(chatRoomId));
        return super.findOne(query);
    }

    public List<USER_CHAT_ROOM> findByUserId(long userId) {
        Query query = Query.query(where(MongoField.USER_ID).is(userId));
        query.with(Sort.by(Sort.Order.desc(MongoField.UPDATE_TIME)));
        return super.find(query);
    }
}
