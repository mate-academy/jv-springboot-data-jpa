package mate.academy.springboot.datajpa.repository.specification.product;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceBetweenSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "priceBetween";
    private static final String FIELD_NAME = "price";
    private static final int PRICE_FROM_INDEX = 0;
    private static final int PRICE_TO_INDEX = 1;

    @Override
    public Specification<Product> getSpecification(String[] prices) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(
                root.get(FIELD_NAME),
                new BigDecimal(prices[PRICE_FROM_INDEX]),
                new BigDecimal(prices[PRICE_TO_INDEX]));
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
