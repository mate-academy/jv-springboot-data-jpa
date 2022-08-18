package mate.academy.springboot.datajpa.repository.specification.product;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import javax.persistence.criteria.CriteriaBuilder;

@Component
public class ProductCategorySpecification implements SpecificationProvider<Product> {
    private final static String FILTER_KEY = "categoryIn";
    private final static String FIELD_NAME = "category";

    @Override
    public Specification<Product> getSpecification(String[] categories) {
        return (root, query, cb) -> {
            CriteriaBuilder.In<String> predicate = cb.in(root.get(FIELD_NAME));
            for (String category : categories) {
                predicate.value(category);
            }
            return cb.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
