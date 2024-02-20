package peoplehere.peoplehere.controller.dto.place;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peoplehere.peoplehere.controller.dto.image.PostImageRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostPlaceRequest {
    private String placeName;
//    private List<PostImageRequest> placeImage;
    private String placeAddress;
    private LatLngDto latLng;
    private int placeOrder;
}