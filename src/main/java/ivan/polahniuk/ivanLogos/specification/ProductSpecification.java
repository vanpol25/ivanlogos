package ivan.polahniuk.ivanLogos.specification;

import ivan.polahniuk.ivanLogos.dto.request.ProductCriteria;
import ivan.polahniuk.ivanLogos.entity.Category;
import ivan.polahniuk.ivanLogos.entity.City;
import ivan.polahniuk.ivanLogos.entity.Product;
import ivan.polahniuk.ivanLogos.entity.SubCategory;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;

public class ProductSpecification implements Specification<Product> {

    private String name;
    private Integer minPrice;
    private Integer maxPrice;
    private Long subCategoryId;
    private Long categoryId;
    private Long cityId;

    public ProductSpecification(ProductCriteria criteria) {
        name = criteria.getName();
        minPrice = criteria.getMinPrice();
        maxPrice = criteria.getMaxPrice();
        subCategoryId = criteria.getSubCategoryId();
        categoryId = criteria.getCategoryId();
        cityId = criteria.getCityId();
    }

    @Override
    public Predicate toPredicate(Root<Product> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        Predicate p1 = findByName(r, cb);
        Predicate p2 = findByPrice(r, cb);
        Predicate p3 = findBySubCategoryOrCategory(r, cb);
        Predicate p4 = findByCity(r, cb);

        return cb.and(p1, p2, p3, p4);
    }

    private Predicate findByName(Root<Product> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (name != null) {
            predicate = cb.like(r.get("name"), '%' + name + '%');
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

    private Predicate findByPrice(Root<Product> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (minPrice != null && maxPrice != null) {
            predicate = cb.between(r.get("price"), minPrice, maxPrice);
        } else if (minPrice != null) {
            predicate = cb.greaterThanOrEqualTo(r.get("price"), minPrice);
        } else if (maxPrice != null) {
            predicate = cb.lessThanOrEqualTo(r.get("price"), maxPrice);
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

    private Predicate findBySubCategoryOrCategory(Root<Product> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (categoryId != null) {
            Join<Product, SubCategory> subCategoryJoin = r.join("subCategory");
            Join<SubCategory, Category> categoryJoin = subCategoryJoin.join("category");
            predicate = cb.equal(categoryJoin.get("id"), categoryId);
        } else
        if (subCategoryId != null) {
            Join<Product, SubCategory> subCategoryJoin = r.join("subCategory");
            predicate = cb.equal(subCategoryJoin.get("id"), subCategoryId);
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

    private Predicate findByCity(Root<Product> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (cityId != null) {
            Join<Product, City> cityJoin = r.join("city");
            predicate = cb.equal(cityJoin.get("id"), cityId);
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }
}
