package mate.academy.springboot.datajpa.repository.specification.product;

import javax.persistence.criteria.CriteriaBuilder;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryInSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "categoryIn";
    private static final String FIELD_NAME = "color";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(root.get(FIELD_NAME));
            for (String value : params) {
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
