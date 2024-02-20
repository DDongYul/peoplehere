package peoplehere.peoplehere.service;

import java.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peoplehere.peoplehere.controller.dto.place.GetPlaceResponse;
import peoplehere.peoplehere.controller.dto.place.PlaceDtoConverter;
import peoplehere.peoplehere.controller.dto.image.PostImageRequest;
import peoplehere.peoplehere.controller.dto.place.PostPlaceRequest;
import peoplehere.peoplehere.domain.Place;
import peoplehere.peoplehere.repository.PlaceRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PlaceService {

    private final S3Service s3Service;
    private final PlaceRepository placeRepository;

    //이미지를 Decoding 하고 S3에 저장한 뒤, 저장 된 URL을 place에 담아줌
//    public void saveImages(Place place, PostPlaceRequest postPlaceRequests) {
//        for (PostImageRequest imageRequest : postPlaceRequests.getPlaceImage()) {
//            byte[] decodingImage = decodeBase64ToFile(imageRequest.getEncodingString()); //decoding
//            String storedFileName = s3Service.saveByteArrayToS3(decodingImage,
//                imageRequest.getOriginalFileName()); //S3에 파일 저장
//            place.addImageUrls(s3Service.getPictureS3Url(storedFileName));  //DB에는 파일 URL만 저장
//        }
//    }

    //장소 객체를 만들고, 이미지 저장 로직을 거쳐 객체를 DB에 저장
    public Place createPlace(PostPlaceRequest postPlaceRequest) {
        Place place = PlaceDtoConverter.postPlaceRequestToPlace(postPlaceRequest);
//        saveImages(place, postPlaceRequest);
        placeRepository.save(place);
        return place;
    }

    //Base64로 decoding 하기 위한 메서드
    public byte[] decodeBase64ToFile(String encodingString) {
        return Base64.getDecoder().decode(encodingString);
    }

    public GetPlaceResponse getPlace(Long id) {
        Place place = placeRepository.findById(id).orElseThrow();
        return new GetPlaceResponse(place.getTour().getId(), id, place.getImageUrls(), place.getAddress(), place.getOrder());
    }
}
