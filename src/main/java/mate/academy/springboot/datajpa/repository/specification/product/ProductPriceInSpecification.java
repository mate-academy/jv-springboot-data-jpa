package mate.academy.springboot.datajpa.repository.specification.product;

import jakarta.persistence.criteria.CriteriaBuilder;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceInSpecification implements SpecificationProvider<Product> {
    public static final String FILTER_KEY = "priceIn";
    public static final String FIELD_NAME = "price";

    @Override
    public Specification<Product> getSpecification(String[] prices) {
        return (root, query, cb) -> {
            CriteriaBuilder.In<String> predicate = cb.in(root.get(FIELD_NAME));
            for (String value : prices) {
                predicate.value(value);
            }
            return cb.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
