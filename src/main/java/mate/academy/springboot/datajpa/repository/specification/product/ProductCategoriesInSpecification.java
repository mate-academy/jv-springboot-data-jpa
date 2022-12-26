package mate.academy.springboot.datajpa.repository.specification.product;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoriesInSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "categoryIn";
    private static final String FIELD_NAME = "title";
    private static final String ATTRIBUTE_NAME = "category";

    @Override
    public Specification<Product> getSpecification(String[] categories) {
        return (root, query, criteriaBuilder) -> {
            Join<Product, Category> join = root.join(ATTRIBUTE_NAME, JoinType.INNER);
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(join.get(FIELD_NAME));
            for (String category : categories) {
                predicate.value(category);
            }
            return criteriaBuilder.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
