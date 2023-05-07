package mate.academy.springboot.datajpa.repository.specification.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class ProductPriceBetweenSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "priceBetween";
    private static final String FILTER_NAME = "price";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Specification<Product> getSpecification(String[] params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        double minPrice = Double.parseDouble(params[0]);
        double maxPrice = Double.parseDouble(params[1]);
        return (root, query, criteriaBuilder) ->
            cb.between(root.get(FILTER_NAME), minPrice, maxPrice);
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
