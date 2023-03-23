package mate.academy.springboot.datajpa.repository.specification.product;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceFromOrEqualsSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "priceFrom";
    private static final String FIELD_KEY = "price";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return ((root, query, cb) -> cb.greaterThanOrEqualTo(root.get(FIELD_KEY), params[0]));
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
