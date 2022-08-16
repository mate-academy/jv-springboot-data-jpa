package mate.academy.springboot.datajpa.specification.product;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceToSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "priceTo";
    private static final String FIELD_NAME = "price";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, cb) -> cb.lessThan(root.get(FIELD_NAME),
                BigDecimal.valueOf(Double.parseDouble(params[0])));
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
