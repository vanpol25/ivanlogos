package ivan.polahniuk.ivanLogos.controller;

import ivan.polahniuk.ivanLogos.dto.request.PhotoRequest;
import ivan.polahniuk.ivanLogos.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @PostMapping
    public void create(@Valid @RequestBody PhotoRequest request) throws IOException {
        photoService.create(request);
    }

    @PutMapping
    public void updateMainImg(Long id) {
        photoService.updateMainImg(id);
    }

    @DeleteMapping
    public void delete(Long id) throws IOException {
        photoService.delete(id);
    }

}
