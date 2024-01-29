package mate.academy.springboot.datajpa.repository.specification.product;

import java.math.BigDecimal;
import javax.persistence.criteria.CriteriaBuilder;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "priceIn";
    private static final String FILTER_NAME = "price";

    @Override
    public Specification<Product> getSpecification(String[] prices) {
        return (root, query, cb) -> {
            CriteriaBuilder.In<BigDecimal> predicate = cb.in(root.get(FILTER_NAME));
            for (String value : prices) {
                predicate.value(BigDecimal.valueOf(Long.parseLong(value)));
            }
            return cb.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
