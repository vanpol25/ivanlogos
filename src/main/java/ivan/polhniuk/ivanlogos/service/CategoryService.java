package ivan.polhniuk.ivanlogos.service;

import ivan.polhniuk.ivanlogos.dto.request.CategoryRequest;
import ivan.polhniuk.ivanlogos.dto.response.CategoryResponse;
import ivan.polhniuk.ivanlogos.entity.Category;
import ivan.polhniuk.ivanlogos.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void create(CategoryRequest request) {
        Category category = new Category();
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
