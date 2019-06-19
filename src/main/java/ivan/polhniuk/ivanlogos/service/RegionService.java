package ivan.polhniuk.ivanlogos.service;

import ivan.polhniuk.ivanlogos.dto.request.RegionRequest;
import ivan.polhniuk.ivanlogos.entity.Region;
import ivan.polhniuk.ivanlogos.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public void create(RegionRequest request) {
        Region region = new Region();
        region.setName(request.getName());
        regionRepository.save(region);
    }

    public Region findById(Long id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Region with id=" + id + " not exists"));
    }

}
