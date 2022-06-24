package mate.academy.springboot.datajpa.dao.specification.product;

import java.util.Arrays;
import javax.persistence.criteria.Predicate;
import mate.academy.springboot.datajpa.dao.specification.SpecificationProvider;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryToSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "to";
    private static final String FIELD_NAME = "price";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            Long[] array = Arrays.stream(params).map(Long::valueOf).toArray(Long[]::new);
            Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get(FIELD_NAME), array[0]);
            return criteriaBuilder.and(predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
