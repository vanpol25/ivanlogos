package ivan.polhniuk.ivanlogos.service;

import ivan.polhniuk.ivanlogos.dto.request.CityRequest;
import ivan.polhniuk.ivanlogos.entity.City;
import ivan.polhniuk.ivanlogos.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RegionService regionService;

    public void create(CityRequest request) {
        City city = new City();
        city.setName(request.getName());
        city.setRegion(regionService.findById(request.getRegionId()));
        cityRepository.save(city);
    }

}
