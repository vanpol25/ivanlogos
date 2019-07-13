package ivan.polhniuk.ivanlogos.controller;

import ivan.polhniuk.ivanlogos.dto.request.CityRequest;
import ivan.polhniuk.ivanlogos.dto.response.CityResponse;
import ivan.polhniuk.ivanlogos.service.CityService;
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
