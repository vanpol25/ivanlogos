package ivan.polahniuk.ivanLogos.service;

import ivan.polahniuk.ivanLogos.dto.request.ProductCriteria;
import ivan.polahniuk.ivanLogos.dto.response.ProductFullResponse;
import ivan.polahniuk.ivanLogos.entity.Product;
import ivan.polahniuk.ivanLogos.repository.ProductRepository;
import ivan.polahniuk.ivanLogos.config.Lorem;
import ivan.polahniuk.ivanLogos.dto.request.PaginationRequest;
import ivan.polahniuk.ivanLogos.dto.request.ProductRequest;
import ivan.polahniuk.ivanLogos.dto.response.PageResponse;
import ivan.polahniuk.ivanLogos.dto.response.ProductResponse;
import ivan.polahniuk.ivanLogos.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    public static final String IMG_DIR =
            System.getProperty("user.home") + File.separator +
                    "shop-images" + File.separator;

    public static final String EMPTY_IMG = "empty.png";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @Autowired
    private PhotoService photoService;

    public Long create(ProductRequest request) {
       return save(new Product(), request);
    }

    public Long update(Long id, ProductRequest request) {
         return save(findById(id), request);
    }

    public void delete(Long id) {
        Product product = findById(id);
        product.getPhotos().forEach(o-> {
            try {
                photoService.delete(o.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        productRepository.delete(product);
    }

    private Long save(Product product, ProductRequest request) {
        product.setName(request.getName());
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        } else {
            product.setDescription(Lorem.LOREM);
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
        if (product.getMainImg() == null) {
            product.setMainImg(EMPTY_IMG);
        }
        productRepository.save(product);
        return product.getId();
    }

    public List<ProductResponse> findByCriteria(ProductCriteria criteria) {
        return productRepository.findAll(new ProductSpecification(criteria), criteria.getPaginationRequest().toPageable())
                .stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    public PageResponse<ProductResponse> findPage(PaginationRequest paginationRequest) {
        Page<Product> page = productRepository.findAll(paginationRequest.toPageable());
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
        updateReviews(id);
        return new ProductFullResponse(findById(id));
    }

}
