package ivan.polhniuk.ivanlogos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlPageController {

    @RequestMapping("categories")
    public String category() {
        return "categories.html";
    }
}

