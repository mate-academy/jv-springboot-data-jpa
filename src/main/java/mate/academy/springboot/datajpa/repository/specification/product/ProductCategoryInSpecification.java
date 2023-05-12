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
    private static final String FILTER_KEY = "categoryIn";
    private static final String FIELD_NAME = "name";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, cb) -> {
            Join<Product, Category> join = root.join("category", JoinType.INNER);
            CriteriaBuilder.In<String> predicate = cb.in(join.get(FIELD_NAME));
            for (String value : params) {
                predicate.value(value);
            }
            return predicate;
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
