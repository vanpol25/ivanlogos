package ivan.polahniuk.ivanLogos.service;

import ivan.polahniuk.ivanLogos.dto.request.CategoryRequest;
import ivan.polahniuk.ivanLogos.entity.Category;
import ivan.polahniuk.ivanLogos.repository.CategoryRepository;
import ivan.polahniuk.ivanLogos.dto.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void create(CategoryRequest request) {
        save(new Category(), request);
    }

    public void update(Long id, CategoryRequest request) {
        save(findById(id), request);
    }

    public void delete(Long id) {
        categoryRepository.delete(findById(id));
    }

    private void save(Category category, CategoryRequest request) {
        category.setName(request.getName());
        categoryRepository.save(category);
    }

    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category with id=" + id + " not exists"));
    }

}
