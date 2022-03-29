package mate.academy.springboot.datajpa.repository.specification.product;

import javax.persistence.criteria.Predicate;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductMaxPriceSpecification implements SpecificationProvider<Product> {
    public static final String FILTER_KEY = "maxPrice";
    public static final String FIELD_NAME = "price";

    @Override
    public Specification<Product> getSpecification(String[] categoryNames) {
        return (root, query, criteriaBuilder) -> {
            Predicate lessThanPrice = criteriaBuilder
                    .lessThanOrEqualTo(root.get(FIELD_NAME), categoryNames[0]);
            return criteriaBuilder.and(lessThanPrice);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
