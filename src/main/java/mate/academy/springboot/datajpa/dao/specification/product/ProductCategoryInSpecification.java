package mate.academy.springboot.datajpa.dao.specification.product;

import java.util.Arrays;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import mate.academy.springboot.datajpa.dao.specification.SpecificationProvider;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryInSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "categoryIn";
    private static final String FIELD_NAME = "id";

    @Override
    public Specification<Product> getSpecification(String[] categories) {
        return (root, query, criteriaBuilder) -> {
            Long[] array = Arrays.stream(categories).map(Long::valueOf).toArray(Long[]::new);
            Join<Product, Category> join = root.join("category", JoinType.INNER);
            CriteriaBuilder.In<Long> predicate = criteriaBuilder.in(join.get(FIELD_NAME));
            for (Long value : array) {
                predicate.value(value);
            }
            return criteriaBuilder.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
