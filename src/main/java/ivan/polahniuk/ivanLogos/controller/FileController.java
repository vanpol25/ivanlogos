package ivan.polahniuk.ivanLogos.controller;

import ivan.polahniuk.ivanLogos.dto.request.FileRequest;
import ivan.polahniuk.ivanLogos.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public String upload(@Valid @RequestBody FileRequest fileRequest) throws IOException {
        return fileService.saveFile(fileRequest.getData(), fileRequest.getFileName());
    }
}
