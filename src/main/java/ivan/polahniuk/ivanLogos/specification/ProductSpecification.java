package ivan.polahniuk.ivanLogos.specification;

import ivan.polahniuk.ivanLogos.dto.request.ProductCriteria;
import ivan.polahniuk.ivanLogos.entity.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ProductSpecification implements Specification<Product> {

    private ProductCriteria criteria;

    public ProductSpecification(ProductCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Product> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        Predicate p1 = findByName(r, cb);
        Predicate p2 = findByPrice(r, cb);
        Predicate p3 = findBySubCategoryOrCategory(r, cb);
        Predicate p4 = findByCityOrRegion(r, cb);

        return cb.and(p1, p2, p3, p4);
    }

    private Predicate findByName(Root<Product> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (criteria.getName() != null) {
            predicate = cb.like(r.get("name"), '%' + criteria.getName() + '%');
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

    private Predicate findByPrice(Root<Product> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (criteria.getMinPrice() != null && criteria.getMaxPrice() != null) {
            predicate = cb.between(r.get("price"), criteria.getMinPrice(), criteria.getMaxPrice());
        } else if (criteria.getMinPrice() != null) {
            predicate = cb.greaterThanOrEqualTo(r.get("price"), criteria.getMinPrice());
        } else if (criteria.getMaxPrice() != null) {
            System.out.println("min price is null");
            predicate = cb.lessThanOrEqualTo(r.get("price"), criteria.getMaxPrice());
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

    private Predicate findBySubCategoryOrCategory(Root<Product> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (criteria.getCategoryId() != null) {
            Join<Product, SubCategory> subCategoryJoin = r.join("subCategory");
            Join<SubCategory, Category> categoryJoin = subCategoryJoin.join("category");
            predicate = cb.equal(categoryJoin.get("id"), criteria.getCategoryId());
        } else if (criteria.getSubCategoryId() != null) {
            Join<Product, SubCategory> subCategoryJoin = r.join("subCategory");
            predicate = cb.equal(subCategoryJoin.get("id"), criteria.getSubCategoryId());
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

    private Predicate findByCityOrRegion(Root<Product> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (criteria.getRegionId() != null) {
            Join<Product, City> cityJoin = r.join("city");
            Join<City, Region> regionJoin = cityJoin.join("region");
            predicate = cb.equal(regionJoin.get("id"), criteria.getRegionId());
        } else if (criteria.getCityId() != null) {
            Join<Product, City> cityJoin = r.join("city");
            predicate = cb.equal(cityJoin.get("id"), criteria.getCityId());
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }
}
