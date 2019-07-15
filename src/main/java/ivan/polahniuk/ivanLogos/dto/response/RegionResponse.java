package ivan.polahniuk.ivanLogos.dto.response;

import ivan.polahniuk.ivanLogos.entity.Region;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegionResponse {

    private Long id;
    private String name;

    public RegionResponse(Region region) {
        id = region.getId();
        name = region.getName();
    }
}
