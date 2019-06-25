package ivan.polhniuk.ivanlogos.service;

import ivan.polhniuk.ivanlogos.dto.request.SubCategoryRequest;
import ivan.polhniuk.ivanlogos.dto.response.SubCategoryResponse;
import ivan.polhniuk.ivanlogos.entity.SubCategory;
import ivan.polhniuk.ivanlogos.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;


    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public void create(SubCategoryRequest request) {
        SubCategory subCategory = new SubCategory();
        subCategory.setName(request.getName());
        subCategory.setCategory(categoryService.findById(request.getCategoryId()));
        subCategoryRepository.save(subCategory);
    }

    public List<SubCategoryResponse> findAll() {
        return subCategoryRepository.findAll().stream()
                .map(SubCategoryResponse::new)
                .collect(Collectors.toList());
    }

    public SubCategory findById(Long id) {
        return subCategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SubCategory with id=" + id + " not exists"));
    }
}
