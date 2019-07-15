package ivan.polahniuk.ivanLogos.controller;

import ivan.polahniuk.ivanLogos.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @DeleteMapping
    public void delete(Long id) {
        photoService.delete(id);
    }

}
