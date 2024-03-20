package peoplehere.peoplehere.controller.dto.tour;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peoplehere.peoplehere.controller.dto.place.PlaceInfoDto;
import peoplehere.peoplehere.controller.dto.review.GetReviewResponse;
import peoplehere.peoplehere.controller.dto.user.UserDetailInfoDto;
import peoplehere.peoplehere.domain.enums.Status;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTourResponse {
    private Long tourId;
    private String tourName;
    private boolean isWished;
    private Long userId;
    private String userName;
    private String userImageUrl;
    private String userContents;
    private int tourTime;
    private String tourContent;

    private Map<String, String> questions;
    private List<GetReviewResponse> getReviewResponses;

    private List<PlaceInfoDto> places;
    private List<String> categoryNames;
    private List<UserDetailInfoDto> participants;
    private Status status;
}
