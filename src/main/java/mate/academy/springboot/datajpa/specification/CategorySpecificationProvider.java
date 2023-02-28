package mate.academy.springboot.datajpa.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CategorySpecificationProvider implements SpecificationProvider<Product> {
    private static final String FIELD_NAME = "name";
    private static final String FILTER_KEY = "categoryIn";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            Join<Product, Category> categoryJoin = root.join("category", JoinType.INNER);
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(categoryJoin.get(FIELD_NAME));
            for (String values : params) {
                predicate.value(values);
            }
            return criteriaBuilder.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
