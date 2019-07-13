package ivan.polhniuk.ivanlogos.controller;

import ivan.polhniuk.ivanlogos.dto.request.PhotoRequest;
import ivan.polhniuk.ivanlogos.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @PostMapping
    public void create(@Valid @RequestBody PhotoRequest request) {
        photoService.create(request);
    }

}
