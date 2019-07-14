package ivan.polhniuk.ivanlogos.controller;

import ivan.polhniuk.ivanlogos.dto.request.PaginationRequest;
import ivan.polhniuk.ivanlogos.dto.request.ProductRequest;
import ivan.polhniuk.ivanlogos.dto.response.PageResponse;
import ivan.polhniuk.ivanlogos.dto.response.ProductFullResponse;
import ivan.polhniuk.ivanlogos.dto.response.ProductResponse;
import ivan.polhniuk.ivanlogos.service.ProductService;
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
