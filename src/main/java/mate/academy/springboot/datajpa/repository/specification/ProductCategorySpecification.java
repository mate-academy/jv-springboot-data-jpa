package mate.academy.springboot.datajpa.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductCategorySpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "category";
    private static final String FIELD_NAME = "name";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, cb) -> {
            Join<Product, Category> join = root.join(FILTER_KEY, JoinType.INNER);
            CriteriaBuilder.In<String> predicate = cb.in(join.get(FIELD_NAME));
            for (String value : params) {
                predicate.value(value);
            }
            return predicate;
        };
    }

    public String getFilterKey() {
        return FILTER_KEY;
    }
}
