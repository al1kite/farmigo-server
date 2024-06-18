package com.farmigo.server.domain.farm.model.vo;

import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_REVIEW;
import com.farmigo.server.global.model.vo.UserInfo;
import com.farmigo.server.global.util.DateUtil;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @Description : 리뷰 정보 Vo
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private String reviewId;
    private String activityId;
    private String review;
    private LocalDateTime reviewDate;
    private int score;  // 10점만점

    private UserInfo userInfo;

    public Review(USER_ACTIVITY_REVIEW review) {
        this.reviewId = review.getReviewId();
        this.activityId = review.getActivityId();
        this.review = review.getReview();
        this.reviewDate = DateUtil.epochToDate(review.getCreateTime());
        this.score = review.getScore();
    }
}
