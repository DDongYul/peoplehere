package peoplehere.peoplehere.controller.dto.tour;

import java.util.ArrayList;
import java.util.Map;
import peoplehere.peoplehere.controller.dto.place.PlaceInfoDto;
import peoplehere.peoplehere.controller.dto.review.GetReviewResponse;
import peoplehere.peoplehere.controller.dto.user.UserDetailInfoDto;
import peoplehere.peoplehere.controller.dto.user.UserDtoConverter;
import peoplehere.peoplehere.controller.dto.user.UserInfoDto;
import peoplehere.peoplehere.domain.Tour;
import peoplehere.peoplehere.domain.Place;
import peoplehere.peoplehere.domain.TourHistory;
import peoplehere.peoplehere.domain.User;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import peoplehere.peoplehere.domain.UserQuestion;
import peoplehere.peoplehere.domain.UserReview;

public class TourDtoConverter {
    public static Tour postTourRequestToTour(PostTourRequest postTourRequest) {
        Tour tour = new Tour(postTourRequest.getTourName(),
                postTourRequest.getTourTime(),postTourRequest.getTourContent());
        return tour;

    }

    // 주소에서 '~구' 또는 '~시'만 추출하는 메소드
    public static String extractDistrictOrCity(String address) {
        // '구' 단위로 주소가 끝나는 부분을 찾아 해당 부분만 반환
        int districtIndex = address.lastIndexOf("구");
        if (districtIndex != -1) { // '구'가 발견되면
            return address.substring(address.substring(0, districtIndex).lastIndexOf(" ") + 1, districtIndex + 1);
        }

        // '구' 단위가 없을 경우, '시' 단위로 주소를 잘라내어 반환
        int cityIndex = address.lastIndexOf("시");
        if (cityIndex != -1) { // '시'가 발견되면
            return address.substring(address.substring(0, cityIndex).lastIndexOf(" ") + 1, cityIndex + 1);
        }
        // '구'나 '시' 단위가 없는 경우, 전체 주소 반환
        return address;
    }

    public static GetTourResponse tourToGetTourResponse(Tour tour, boolean isWished) {
        GetTourResponse getTourResponse = new GetTourResponse();
        getTourResponse.setTourId(tour.getId());
        getTourResponse.setTourName(tour.getName());
        getTourResponse.setWished(isWished);
        getTourResponse.setUserId(tour.getUser().getId());
        getTourResponse.setUserName(tour.getUser().getFirstName());
        getTourResponse.setUserImageUrl(tour.getUser().getImageUrl());
        getTourResponse.setUserContents(tour.getUser().getContent());
        getTourResponse.setTourTime(tour.getTime());
        getTourResponse.setTourContent(tour.getContent());

        // 투어에 속한 장소들을 PlaceInfoDto로 변환하여 추가
        List<PlaceInfoDto> placeInfoDtos = tour.getPlaces().stream()
                .sorted(Comparator.comparingInt(Place::getOrder))
                .map(place -> new PlaceInfoDto(place.getId(), place.getContent(), place.getImageUrls(), extractDistrictOrCity(place.getAddress()), place.getLatLng(), place.getOrder()))
                .toList();
        getTourResponse.setPlaces(placeInfoDtos);

        // 투어의 카테고리 이름을 추출하여 추가
        List<String> categoryNames = tour.getTourCategories().stream()
                .map(tc -> tc.getCategory().getName())
                .toList();
        getTourResponse.setCategoryNames(categoryNames);

        // 투어의 참여 유저 리스트 추가
        List<UserDetailInfoDto> participants = new ArrayList<>();
        for (TourHistory tourHistory : tour.getTourHistories()) {
            User user = tourHistory.getUser();
            List<String> languages = user.getLanguages()
                .stream()
                .map(language -> language.getLanguage().getKoreanName())
                .collect(Collectors.toList());

            UserDetailInfoDto userDetailInfoDto = new UserDetailInfoDto(user.getId(),
                user.getFirstName(), user.getImageUrl(),
                languages);

            participants.add(userDetailInfoDto);
        }
        getTourResponse.setParticipants(participants);

        //투어 리더의 문답 추가
        User leader = tour.getUser();
        Map<String, String> questions = leader.getUserQuestions()
            .stream()
            .collect(Collectors.toMap(
                userQuestion -> userQuestion.getQuestion().getQuestion(),
                UserQuestion::getAnswer
            ));
        getTourResponse.setQuestions(questions);

        //투어의 리뷰 추가
        List<GetReviewResponse> reviews = new ArrayList<>();
        List<UserReview> userReviews = leader.getUserReviews();
        for (UserReview userReview : userReviews) {
            User user = userReview.getUser();
            UserInfoDto userInfoDto = new UserInfoDto(user.getId(), user.getFirstName(),
                user.getImageUrl());
            reviews.add(new GetReviewResponse(userReview.getId(), userInfoDto,
                userReview.getReview().getContent(), userReview.getCreatedAt()));
        }
        getTourResponse.setGetReviewResponses(reviews);




       // 투어 상태, 생성 및 수정 날짜 설정
        getTourResponse.setStatus(tour.getStatus());

        return getTourResponse;
    }
}