package mate.academy.springboot.datajpa.repository.specification.product;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceBeforeOrEqualsSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "priceBefore";
    private static final String FIELD_NAME = "price";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return ((root, query, cb) -> {
            return cb.lessThanOrEqualTo(root.get(FIELD_NAME), params[0]);
        });
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
