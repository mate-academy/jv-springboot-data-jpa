package mate.academy.springboot.datajpa.repository.specification.product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ProductCategoryInSpecification implements SpecificationProvider<Product> {
    public static final String FILTER_KEY = "categoryIn";
    public static final String FIELD_NAME = "name";

    @Override
    public Specification<Product> getSpecification(String[] categoryNames) {
        return (root, query, criteriaBuilder) -> {
            Join<Product, Category> join = root.join("category", JoinType.INNER);
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(join.get(FIELD_NAME));
            for (String value : categoryNames) {
                predicate.value(StringUtils.capitalize(value));
            }
            return criteriaBuilder.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
