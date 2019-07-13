package ivan.polhniuk.ivanlogos.service;

import ivan.polhniuk.ivanlogos.dto.request.CityRequest;
import ivan.polhniuk.ivanlogos.dto.response.CityResponse;
import ivan.polhniuk.ivanlogos.entity.City;
import ivan.polhniuk.ivanlogos.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RegionService regionService;

    public void create(CityRequest request) {
        save(new City(), request);
    }

    public void update(Long id, CityRequest request) {
        save(findById(id), request);
    }

    public void delete(Long id) {
        cityRepository.delete(findById(id));
    }

    private void save(City city, CityRequest request) {
        city.setName(request.getName());
        city.setRegion(regionService.findById(request.getRegionId()));
        cityRepository.save(city);
    }

    public City findById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("City with id=" + id + " not exists"));
    }
    public List<CityResponse> findAll() {
        return cityRepository.findAll().stream().map(CityResponse::new).collect(Collectors.toList());
    }

}
