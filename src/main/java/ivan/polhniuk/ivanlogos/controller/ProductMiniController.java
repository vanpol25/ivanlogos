package ivan.polhniuk.ivanlogos.controller;

import ivan.polhniuk.ivanlogos.dto.response.ProductResponse;
import ivan.polhniuk.ivanlogos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/productmini")
public class ProductMiniController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ProductResponse findOne(long id) {
        return new ProductResponse(productService.findById(id));
    }
}
