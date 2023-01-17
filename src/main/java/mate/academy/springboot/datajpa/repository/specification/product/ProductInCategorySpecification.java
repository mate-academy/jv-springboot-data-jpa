package mate.academy.springboot.datajpa.repository.specification.product;

import jakarta.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductInCategorySpecification<T> implements SpecificationProvider<T> {
    private static final String FILTER_KEY = "categoryIn";
    private static final String FIELD_NAME = "category";
    private static final String CATEGORY_CRITERIA = "category.name";

    @Override
    public Specification<T> getSpecification(String[] categories) {
        return (root, query, criteriaBuilder) -> {
            root.fetch(FIELD_NAME);
            CriteriaBuilder.In<Object> predicate = criteriaBuilder.in(root.get(CATEGORY_CRITERIA));
            Arrays.stream(categories).forEach(predicate::value);
            return criteriaBuilder.and(predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
