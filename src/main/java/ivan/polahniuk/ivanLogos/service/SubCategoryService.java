package ivan.polahniuk.ivanLogos.service;

import ivan.polahniuk.ivanLogos.dto.request.SubCategoryRequest;
import ivan.polahniuk.ivanLogos.entity.SubCategory;
import ivan.polahniuk.ivanLogos.repository.SubCategoryRepository;
import ivan.polahniuk.ivanLogos.dto.response.SubCategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;


    @Autowired
    private CategoryService categoryService;

    public void create(SubCategoryRequest request) {
        save(new SubCategory(), request);
    }

    public void update(Long id, SubCategoryRequest request) {
        save(findById(id), request);
    }

    public void delete(Long id) {
        subCategoryRepository.delete(findById(id));
    }

    private void save(SubCategory subCategory, SubCategoryRequest request) {
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
