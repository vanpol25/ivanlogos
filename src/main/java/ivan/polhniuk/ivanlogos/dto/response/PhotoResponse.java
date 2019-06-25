package ivan.polhniuk.ivanlogos.dto.response;

import ivan.polhniuk.ivanlogos.entity.Photo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PhotoResponse {

    private Long id;
    private String link;
    private Long productId;

    public PhotoResponse(Photo photo) {
        id = photo.getId();
        link = photo.getLink();
        productId = photo.getProduct().getId();
    }
}
