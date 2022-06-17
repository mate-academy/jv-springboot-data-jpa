package mate.academy.springboot.datajpa.repository.specification.product;

import javax.persistence.criteria.CriteriaBuilder;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductTitleSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "titleIn";
    private static final String FILTER_NAME = "title";

    @Override
    public Specification<Product> getSpecification(String[] titles) {
        return (root, query, cb) -> {
            CriteriaBuilder.In<String> predicate = cb.in(root.get(FILTER_NAME));
            for (String value : titles) {
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
