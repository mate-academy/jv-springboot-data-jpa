package mate.academy.springboot.datajpa.repository.specification.product;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;

@Component
public class ProductMinPriceSpecification implements SpecificationProvider<Product> {
    public static final String FILTER_KEY = "minPrice";
    public static final String FIELD_NAME = "price";
    @Override
    public Specification<Product> getSpecification(String[] categoryNames) {
        return (root, query, criteriaBuilder) -> {
            Predicate greaterThanPrice = criteriaBuilder.greaterThanOrEqualTo(root.get(FIELD_NAME), categoryNames[0]);
            return criteriaBuilder.and(greaterThanPrice);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
