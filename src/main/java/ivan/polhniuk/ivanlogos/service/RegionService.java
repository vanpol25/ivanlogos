package ivan.polhniuk.ivanlogos.service;

import ivan.polhniuk.ivanlogos.dto.request.RegionRequest;
import ivan.polhniuk.ivanlogos.dto.response.RegionResponse;
import ivan.polhniuk.ivanlogos.entity.Region;
import ivan.polhniuk.ivanlogos.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public void create(RegionRequest request) {
        save(new Region(), request);
    }

    public void update(Long id, RegionRequest request) {
        save(findById(id), request);
    }

    public void delete(Long id) {
        regionRepository.delete(findById(id));
    }

    private void save(Region region, RegionRequest request) {
        region.setName(request.getName());
        regionRepository.save(region);
    }

    public Region findById(Long id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Region with id=" + id + " not exists"));
    }

    public List<RegionResponse> findAll() {
        return regionRepository.findAll().stream().map(RegionResponse::new).collect(Collectors.toList());
    }

}
