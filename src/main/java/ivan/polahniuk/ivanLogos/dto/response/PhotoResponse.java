package ivan.polahniuk.ivanLogos.dto.response;

import ivan.polahniuk.ivanLogos.entity.Photo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PhotoResponse {

    private Long id;
    private String name;

    public PhotoResponse(Photo photo) {
        id = photo.getId();
        name = photo.getName();
    }
}
