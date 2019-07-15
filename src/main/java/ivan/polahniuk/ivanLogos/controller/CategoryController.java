package ivan.polahniuk.ivanLogos.controller;

import ivan.polahniuk.ivanLogos.dto.request.CategoryRequest;
import ivan.polahniuk.ivanLogos.dto.response.CategoryResponse;
import ivan.polahniuk.ivanLogos.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public void create(@Valid @RequestBody CategoryRequest request) {
        categoryService.create(request);
    }

    @PutMapping
    public void update(Long id, @Valid @RequestBody CategoryRequest request) {
        categoryService.update(id, request);
    }

    @DeleteMapping
    public void delete(Long id) {
        categoryService.delete(id);
    }

    @GetMapping
    public List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }

}
