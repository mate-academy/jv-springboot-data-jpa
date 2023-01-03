package mate.academy.springboot.datajpa.repository.specification.product;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryInSpecification implements SpecificationProvider<Product> {
    private static final String CATEGORY = "category";
    private static final String KEY = "categoryIn";
    private static final String FIELD = "name";

    @Override
    public Specification<Product> getSpecification(String[] categories) {
        return (root, query, criteriaBuilder) -> {
            Join<Product, Category> join = root.join(CATEGORY, JoinType.INNER);
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(join.get(FIELD));
            for (String value : categories) {
                predicate.value(value);
            }
            return criteriaBuilder.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return KEY;
    }
}
