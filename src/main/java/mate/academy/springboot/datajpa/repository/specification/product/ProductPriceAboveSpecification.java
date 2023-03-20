package mate.academy.springboot.datajpa.repository.specification.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceAboveSpecification implements SpecificationProvider<Product> {

    private static final String FILTER_KEY = "priceFrom";
    private static final String FIELD_NAME = "price";

    @Override
    public Specification<Product> getSpecification(String[] prices) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (String value : prices) {
                Predicate predicate = cb.greaterThan(root.get(FIELD_NAME).as(BigDecimal.class),
                        BigDecimal.valueOf(Long.parseLong(value)));
                predicates.add(predicate);
            }
            return cb.or(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
