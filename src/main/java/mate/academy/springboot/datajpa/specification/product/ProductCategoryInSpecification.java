package mate.academy.springboot.datajpa.specification.product;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryInSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "categoryIn";
    private static final String ATTRIBUTE_NAME = "category";
    private static final String FIELD_NAME = "name";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            Join<Product, Category> join = root.join(ATTRIBUTE_NAME, JoinType.INNER);
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(join.get(FIELD_NAME));
            for (String param : params) {
                predicate.value(param);
            }
            return criteriaBuilder.and(predicate,predicate);
        };
    }

    @Override
    public String getFilerKey() {
        return FILTER_KEY;
    }
}
