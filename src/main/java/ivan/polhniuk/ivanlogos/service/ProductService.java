package ivan.polhniuk.ivanlogos.service;

import ivan.polhniuk.ivanlogos.config.Lorem;
import ivan.polhniuk.ivanlogos.dto.request.PaginationRequest;
import ivan.polhniuk.ivanlogos.dto.request.ProductRequest;
import ivan.polhniuk.ivanlogos.dto.response.PageResponse;
import ivan.polhniuk.ivanlogos.dto.response.ProductResponse;
import ivan.polhniuk.ivanlogos.dto.response.ProductFullResponse;
import ivan.polhniuk.ivanlogos.entity.Product;
import ivan.polhniuk.ivanlogos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    public void create(ProductRequest request) {
        productRepository.save(save(null, request));
    }

    public void update(Long id, ProductRequest request) {
        productRepository.save(save(findById(id), request));
    }

    public void delete(Long id) {
        productRepository.delete(findById(id));
    }

    public PageResponse<ProductResponse> findPage(PaginationRequest paginationRequest) {
        Page<Product> page = productRepository.findAll(paginationRequest.toPageable());
        page.forEach(e -> updateReviews(e.getId()));
        return new PageResponse<>(page.getTotalPages(),
                page.getTotalElements(),
                page.get().
                        map(ProductResponse::new).
                        collect(Collectors.toList()));
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id=" + id + " not exists"));
    }

    private void updateReviews(Long id) {
        Product product = findById(id);
        product.setReviews(product.getReviews() + 1);
        productRepository.save(product);
    }

    private Product save(Product product, ProductRequest request) {
        if (product == null) {
            product = new Product();
        }
        product.setName(request.getName());
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        } else {
            product.setDescription(Lorem.lorem);
        }
        product.setPrice(request.getPrice());
        product.setDate_published(Date.valueOf(LocalDate.now()));
        product.setSubCategory(subCategoryService.findById(request.getSubCategoryId()));
        product.setUser(userService.findById(request.getUserId()));
        if (request.getCityId() != null) {
            product.setCity(cityService.findById(request.getCityId()));
        } else {
            product.setCity(userService.findById(request.getUserId()).getCity());
        }
        return product;
    }

    public List<ProductFullResponse> findAll() {
        return productRepository.findAll().stream()
                .map(ProductFullResponse::new)
                .collect(Collectors.toList());
    }

    public ProductFullResponse findOne(Long id) {
        return new ProductFullResponse(findById(id));
    }
}
