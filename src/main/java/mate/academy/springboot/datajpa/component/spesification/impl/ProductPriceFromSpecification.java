package mate.academy.springboot.datajpa.component.spesification.impl;

import jakarta.persistence.criteria.Predicate;
import java.math.BigDecimal;
import mate.academy.springboot.datajpa.component.spesification.SpecificationProvider;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceFromSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "priceFrom";
    private static final String FIELD_NAME = "price";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, cb) -> {
            Predicate predicate = cb.greaterThanOrEqualTo(root.get(FIELD_NAME),
                    getPriceFromParams(params));
            return cb.and(predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }

    private BigDecimal getPriceFromParams(String[] params) {
        return BigDecimal.valueOf(Long.valueOf(params[0]));
    }
}
