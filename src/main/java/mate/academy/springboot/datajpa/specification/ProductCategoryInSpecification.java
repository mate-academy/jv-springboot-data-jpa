package mate.academy.springboot.datajpa.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryInSpecification implements SpecificationProvider {
    private static final String FILTER_KEY = "categoryIn";
    private static final String FIELD_NAME = "category";
    private static final String CATEGORY_NAME = "name";

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }

    @Override
    public Specification<Product> getSpecification(String[] categories) {
        return (root, query, cb) -> {
            Join<Product, Category> joinedRoot = root.join(FIELD_NAME, JoinType.INNER);
            CriteriaBuilder.In<String> predicate = cb.in(joinedRoot.get(CATEGORY_NAME));
            for (String category : categories) {
                predicate.value(category);
            }
            return cb.and(predicate, predicate);
        };
    }
}
