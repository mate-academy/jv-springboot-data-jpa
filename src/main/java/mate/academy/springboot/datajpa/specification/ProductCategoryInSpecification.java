package mate.academy.springboot.datajpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryInSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "categoryIn";
    private static final String FIELD_NAME = "category";
    private static final String ENTITY_FIELD_NAME = "name";

    @Override
    public Specification<Product> getSpecification(String[] categories) {
        return ((root, query, cb) -> {
            Join<Product, Category> join = root.join(FIELD_NAME, JoinType.INNER);
            CriteriaBuilder.In<String> predicate = cb.in(join.get(ENTITY_FIELD_NAME));
            for (String value : categories) {
                predicate.value(value);
            }
            return cb.and(predicate, predicate);
        });
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
