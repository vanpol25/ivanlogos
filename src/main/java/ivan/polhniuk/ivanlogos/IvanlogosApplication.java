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

    @PostConstruct
    public void init() {
//        categoryRepository.findAll().forEach(c ->
//                System.out.println(c.getId() + " " + c.getName()));
//        categoryRepository.deleteById(2L);

//        productRepository.findByPrice(10000, 30000).forEach(e->
//                System.out.println(e.getId() + " " +
//                        e.getName() + " " +
//                        e.getPrice()));

//        productRepository.findBySubCategory("Audi").forEach(e ->
//                System.out.println(e.getId() + " " +
//                        e.getName() + " " +
//                        e.getPrice()));
        categoryRepository.findAllByNameLike("%a%").forEach(e->
                System.out.println(e.getName()));
    }

    public static void main(String[] args) {
        SpringApplication.run(IvanlogosApplication.class, args);
    }

}
