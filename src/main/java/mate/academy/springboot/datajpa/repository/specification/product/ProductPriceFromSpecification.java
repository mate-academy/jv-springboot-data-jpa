package mate.academy.springboot.datajpa.repository.specification.product;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceFromSpecification implements SpecificationProvider<Product> {
    private static final int FIRST_VALUE_INDEX = 0;
    private static final String FILTER_KEY = "priceFrom";
    private static final String FIELD_NAME = "price";

    @Override
    public Specification<Product> getSpecification(String[] prices) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
                root.get(FIELD_NAME),
                BigDecimal.valueOf(Double.parseDouble(prices[FIRST_VALUE_INDEX])));
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
