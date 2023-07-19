package mate.academy.springboot.datajpa.repository.specification.product;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductCategorySpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "category";
    private static final String CLASS_FIELD = "category";
    private static final String FIELD_NAME = "name";

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }

    @Override
    public Specification<Product> getSpecification(String[] categories) {
        return (root, query, cb) -> {
            Join<Product, Category> join = root.join(CLASS_FIELD, JoinType.INNER);
            CriteriaBuilder.In<String> predicate = cb.in(join.get(FIELD_NAME));
            for (String category : categories) {
                predicate.value(category);
            }
            return cb.and(predicate, predicate);
        };
    }
}
