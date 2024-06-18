package com.farmigo.server.domain.farm.service;

import com.farmigo.server.domain.farm.model.request.SaveFarmActivityReviewRequest;
import com.farmigo.server.domain.farm.model.vo.Review;
import com.farmigo.server.domain.farm.repository.UserActivityReviewMongoRepository;
import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_REVIEW;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 농장 리뷰 관련 서비스
 */
@Slf4j
@Service
@AllArgsConstructor
public class FarmReviewService {

    private final UserActivityReviewMongoRepository userActivityReviewMongoRepository;

    public List<Review> getReviewList(String activityId, String offset, int limit) {
        List<USER_ACTIVITY_REVIEW> reviewList = userActivityReviewMongoRepository.findByActivityIdAndOffset(activityId, offset, limit);
        // TODO : 유저 정보 추가
        return reviewList.stream().map(Review::new).toList();
    }

    public void createReview(long txTime, SaveFarmActivityReviewRequest req) {
        String reviewId = req.getUserId() + "_" + req.getActivityId();

        USER_ACTIVITY_REVIEW review = USER_ACTIVITY_REVIEW.builder()
                .reviewId(reviewId)
                .activityId(req.getActivityId())
                .userId(req.getUserId())
                .review(req.getReview())
                .createTime(txTime)
                .score(req.getScore())
                .build();

        userActivityReviewMongoRepository.save(review);
    }
}
