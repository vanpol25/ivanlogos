package ivan.polhniuk.ivanlogos;

import ivan.polhniuk.ivanlogos.repository.CategoryRepository;
import ivan.polhniuk.ivanlogos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class IvanlogosApplication {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(IvanlogosApplication.class, args);
    }

}
