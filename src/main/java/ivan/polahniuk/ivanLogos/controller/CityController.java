package ivan.polahniuk.ivanLogos.controller;

import ivan.polahniuk.ivanLogos.dto.request.CityRequest;
import ivan.polahniuk.ivanLogos.service.CityService;
import ivan.polahniuk.ivanLogos.dto.response.CityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public void create(@Valid @RequestBody CityRequest request) {
        cityService.create(request);
    }

    @PutMapping
    public void update(Long id, @Valid @RequestBody CityRequest request) {
        cityService.update(id, request);
    }

    @DeleteMapping
    public void delete(Long id) {
        cityService.delete(id);
    }

    @GetMapping
    public List<CityResponse> findAll() {
        return cityService.findAll();
    }

}
