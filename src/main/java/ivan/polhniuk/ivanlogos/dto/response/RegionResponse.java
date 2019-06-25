package ivan.polhniuk.ivanlogos.dto.response;

import ivan.polhniuk.ivanlogos.entity.Region;
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
