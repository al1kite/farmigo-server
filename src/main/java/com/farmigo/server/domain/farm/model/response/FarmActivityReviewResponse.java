package com.farmigo.server.domain.farm.model.response;

import com.farmigo.server.domain.farm.model.vo.Review;
import com.farmigo.server.global.model.response.Response;
import lombok.*;

import java.util.List;

/**
 * @Description :
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FarmActivityReviewResponse extends Response {
    private List<Review> reviewList;
}
