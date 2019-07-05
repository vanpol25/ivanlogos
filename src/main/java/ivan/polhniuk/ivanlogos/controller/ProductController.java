package ivan.polhniuk.ivanlogos.controller;

import ivan.polhniuk.ivanlogos.dto.request.PaginationRequest;
import ivan.polhniuk.ivanlogos.dto.request.ProductRequest;
import ivan.polhniuk.ivanlogos.dto.response.PageResponse;
import ivan.polhniuk.ivanlogos.dto.response.ProductResponse;
import ivan.polhniuk.ivanlogos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public void create(@RequestBody ProductRequest request) {
        productService.create(request);
    }

    @PutMapping
    public void update(Long id, @RequestBody ProductRequest request) {
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
}
