package ivan.polhniuk.ivanlogos.controller;

import ivan.polhniuk.ivanlogos.dto.request.SubCategoryRequest;
import ivan.polhniuk.ivanlogos.dto.response.SubCategoryResponse;
import ivan.polhniuk.ivanlogos.entity.SubCategory;
import ivan.polhniuk.ivanlogos.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/subCategory")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @PostMapping
    public void create(@Valid @RequestBody SubCategoryRequest request) {
        subCategoryService.create(request);
    }

    @GetMapping
    public List<SubCategoryResponse> findAll() {
        return subCategoryService.findAll();
    }

}
