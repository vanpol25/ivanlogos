package ivan.polhniuk.ivanlogos.service;

import ivan.polhniuk.ivanlogos.dto.request.ProductRequest;
import ivan.polhniuk.ivanlogos.dto.response.CategoryResponse;
import ivan.polhniuk.ivanlogos.dto.response.ProductResponse;
import ivan.polhniuk.ivanlogos.entity.Category;
import ivan.polhniuk.ivanlogos.entity.Product;
import ivan.polhniuk.ivanlogos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SubCategoryService subCategoryService;

    public void create(ProductRequest request) {
        productRepository.save(save(null, request));
    }

    public void update(Long id, ProductRequest request) {
        productRepository.save(save(findById(id), request));
    }

    public void delete(Long id) {
        productRepository.delete(findById(id));
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id=" + id + " not exists"));
    }

    private Product save(Product product, ProductRequest request) {
        if (product == null) {
            product = new Product();
        }
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setSubCategory(subCategoryService.findById(request.getSubCategoryId()));
        return product;
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

}
