package ivan.polahniuk.ivanLogos.controller;

import ivan.polahniuk.ivanLogos.service.RegionService;
import ivan.polahniuk.ivanLogos.dto.request.RegionRequest;
import ivan.polahniuk.ivanLogos.dto.response.RegionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PostMapping
    public void create(@Valid @RequestBody RegionRequest request) {
        regionService.create(request);
    }

    @PutMapping
    public void update(Long id, @Valid @RequestBody RegionRequest request) {
        regionService.update(id, request);
    }

    @DeleteMapping
    public void delete(Long id) {
        regionService.delete(id);
    }

    @GetMapping
    public List<RegionResponse> findAll() {
        return regionService.findAll();
    }

    @GetMapping("one")
    public RegionResponse findOne(Long id) {
        return new RegionResponse(regionService.findById(id));
    }

}
