package ivan.polahniuk.ivanLogos.controller;

import ivan.polahniuk.ivanLogos.dto.request.PaginationRequest;
import ivan.polahniuk.ivanLogos.dto.request.ProductRequest;
import ivan.polahniuk.ivanLogos.dto.response.ProductFullResponse;
import ivan.polahniuk.ivanLogos.service.ProductService;
import ivan.polahniuk.ivanLogos.dto.response.PageResponse;
import ivan.polahniuk.ivanLogos.dto.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public void create(@Valid @RequestBody ProductRequest request) {
        productService.create(request);
    }

    @PutMapping
    public void update(Long id, @Valid @RequestBody ProductRequest request) {
        productService.update(id, request);
    }

    @DeleteMapping
    public void delete(Long id) {
        productService.delete(id);
    }

    @GetMapping
    public PageResponse<ProductResponse> findPage(@Valid PaginationRequest paginationRequest) {
        return productService.findPage(paginationRequest);
    }

    @GetMapping("item")
    public ProductFullResponse findOne(Long id) {
        return new ProductFullResponse(productService.findById(id));
    }

}
