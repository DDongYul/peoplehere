package peoplehere.peoplehere.controller.dto.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostReviewRequest {
    private Long tourId;
    private Long userId;
    private String content;
    private float score;
}
