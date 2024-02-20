package peoplehere.peoplehere.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.locationtech.jts.geom.Point;
import peoplehere.peoplehere.controller.dto.image.PostImageRequest;
import peoplehere.peoplehere.controller.dto.place.PlaceDtoConverter;
import peoplehere.peoplehere.controller.dto.place.PutPlaceRequest;
import peoplehere.peoplehere.domain.util.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

//TODO: 사용할 데이터 넣어서 완성시키기
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Place extends BaseTimeEntity {

    public Place(String content, List<String> imageUrls, String address, Point latLng, int order) {
        this.content = content;
        this.imageUrls = imageUrls;
        this.address = address;
        this.latLng = latLng;
        this.order = order;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    private String content;

    @ElementCollection
    @CollectionTable(name = "place_images", joinColumns = @JoinColumn(name = "place_id"))
    @Column(name = "image_url")
    @Nullable
    private List<String> imageUrls = new ArrayList<>();

    private String address;

    // TODO: 위도 경도 추가하기
    @Column(columnDefinition = "POINT")
    private Point latLng;

    @Column(name = "`order`")
    private int order;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @ColumnDefault("'일반'")
    private String status = "일반";

    public void addImageUrls(String imageUrl){
        imageUrls.add(imageUrl);
    }

    //==연관관계 편의 메서드==//
    public void setTour(Tour tour) {
        this.tour = tour;
        tour.getPlaces().add(this);
    }

    public void update(PutPlaceRequest putPlaceRequest) {
        if (putPlaceRequest.getPlaceName() != null) {
            this.content = putPlaceRequest.getPlaceName();
        }
        if (putPlaceRequest.getPlaceImage() != null && !putPlaceRequest.getPlaceImage().isEmpty()) {
            this.imageUrls = putPlaceRequest.getPlaceImage().stream()
                    .map(PostImageRequest::getOriginalFileName)
                    .toList();
        }
        if (putPlaceRequest.getPlaceAddress() != null) {
            this.address = putPlaceRequest.getPlaceAddress();
        }
        if (putPlaceRequest.getLatLng() != null) {
            this.latLng = PlaceDtoConverter.convertLatLngDtoToPoint(putPlaceRequest.getLatLng());
        }
    }
}
