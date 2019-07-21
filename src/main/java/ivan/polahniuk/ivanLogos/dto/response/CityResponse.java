package ivan.polahniuk.ivanLogos.dto.response;

import ivan.polahniuk.ivanLogos.entity.City;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CityResponse {

    private Long id;
    private String name;
    private RegionResponse region;

    public CityResponse(City city) {
        id = city.getId();
        name = city.getName();
        region = new RegionResponse(city.getRegion());
    }
}
