package ivan.polahniuk.ivanLogos.service;

import ivan.polahniuk.ivanLogos.dto.response.ProductFullResponse;
import ivan.polahniuk.ivanLogos.entity.Product;
import ivan.polahniuk.ivanLogos.repository.ProductRepository;
import ivan.polahniuk.ivanLogos.config.Lorem;
import ivan.polahniuk.ivanLogos.dto.request.PaginationRequest;
import ivan.polahniuk.ivanLogos.dto.request.ProductRequest;
import ivan.polahniuk.ivanLogos.dto.response.PageResponse;
import ivan.polahniuk.ivanLogos.dto.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    public static final String IMG_DIR =
            System.getProperty("user.home") + File.separator +
                    "shop-images" + File.separator;

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
        product.setMainImg("empty.png");
        return product;
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

    public List<ProductFullResponse> findAll() {
        return productRepository.findAll().stream()
                .map(ProductFullResponse::new)
                .collect(Collectors.toList());
    }

    public ProductFullResponse findOne(Long id) {
        return new ProductFullResponse(findById(id));
    }

}
