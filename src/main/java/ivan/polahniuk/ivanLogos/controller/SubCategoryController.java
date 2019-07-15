package ivan.polahniuk.ivanLogos.controller;

import ivan.polahniuk.ivanLogos.dto.request.SubCategoryRequest;
import ivan.polahniuk.ivanLogos.service.SubCategoryService;
import ivan.polahniuk.ivanLogos.dto.response.SubCategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/subCategory")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @PostMapping
    public void create(@Valid @RequestBody SubCategoryRequest request) {
        subCategoryService.create(request);
    }

    @PutMapping
    public void update(Long id, @Valid @RequestBody SubCategoryRequest request) {
        subCategoryService.update(id, request);
    }

    @DeleteMapping
    public void delete(Long id) {
        subCategoryService.delete(id);
    }

    @GetMapping
    public List<SubCategoryResponse> findAll() {
        return subCategoryService.findAll();
    }

}
