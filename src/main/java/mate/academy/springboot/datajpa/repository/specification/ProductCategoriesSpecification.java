package mate.academy.springboot.datajpa.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductCategoriesSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "categories";
    private static final String FIELD_NAME = "category";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, cb) -> {
            Join<Product, Category> categoryJoin = root.join(FIELD_NAME, JoinType.INNER);
            CriteriaBuilder.In<String> predicate = cb.in(categoryJoin.get(FIELD_NAME));
            for (String value : params) {
                predicate.value(value);
            }
            return cb.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
