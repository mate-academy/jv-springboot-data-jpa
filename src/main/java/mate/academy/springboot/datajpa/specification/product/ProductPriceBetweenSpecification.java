package mate.academy.springboot.datajpa.specification.product;

import java.math.BigDecimal;
import javax.persistence.criteria.Predicate;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceBetweenSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "priceBetween";
    private static final String FIELD_NAME = "price";

    @Override
    public Specification<Product> getSpecification(String[] prices) {
        return (root, query, cb) -> {
            Predicate priceFrom = cb.greaterThanOrEqualTo(root.get(FIELD_NAME),
                    new BigDecimal(prices[0]));
            Predicate priceTo = cb.lessThanOrEqualTo((root.get(FIELD_NAME)),
                    new BigDecimal(prices[1]));
            return cb.and(priceFrom, priceTo);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
