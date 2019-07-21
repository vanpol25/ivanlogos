package ivan.polahniuk.ivanLogos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlPageController {

    @RequestMapping("subCategories")
    public String subCategory() {
        return "admin/subCategories.html";
    }

    @RequestMapping("categories")
    public String category() {
        return "admin/categories.html";
    }

    @RequestMapping("cities")
    public String city(){
        return "admin/cities.html";
    }

    @RequestMapping("regions")
    public String region() {
        return "admin/regions.html";
    }

    @RequestMapping("item")
    public String item(Long id) {
        return "admin/productPage.html";
    }

    @RequestMapping("products")
    public String product() {
        return "admin/products.html";
    }

}

