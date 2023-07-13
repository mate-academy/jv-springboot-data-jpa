package mate.academy.springboot.datajpa.repository.specification.product;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryInSpecification implements SpecificationProvider<Product> {
    public static final String FILTER_KEY = "categoryIn";
    public static final String FIELD_NAME = "name";

    @Override
    public Specification<Product> getSpecification(String[] categories) {
        return (root, query, cb) -> {
            Join<Object, Object> join = root.join("category", JoinType.INNER);
            CriteriaBuilder.In<String> predicate = cb.in(join.get(FIELD_NAME));
            for (String value : categories) {
                predicate.value(value);
            }
            return cb.and(predicate,predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
