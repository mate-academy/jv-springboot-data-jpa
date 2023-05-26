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
    private static final String FIELD_NAME = "category";
    private static final String CATEGORY_NAME = "name";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, builder) -> {
            Join<Product, Category> joinedRoot = root.join(FIELD_NAME, JoinType.INNER);
            CriteriaBuilder.In<String> inClause = builder.in(joinedRoot.get(CATEGORY_NAME));
            for (String param : params) {
                inClause.value(param);
            }
            return builder.and(inClause, inClause);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
