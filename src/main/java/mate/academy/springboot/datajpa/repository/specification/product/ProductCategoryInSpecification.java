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
    private static final String FIELD_NAME = "name";
    private static final String FIELD_KEY = "categoryIn";
    private static final String JOINED_COLUMN = "category";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return ((root, query, cb) -> {
            Join<Product, Category> products = root.join(JOINED_COLUMN, JoinType.INNER);
            CriteriaBuilder.In<String> predicate = cb.in(products.get(FIELD_NAME));
            for (String value : params) {
                predicate.value(value);
            }
            return cb.and(predicate, predicate);
        });
    }

    @Override
    public String getFilterKey() {
        return FIELD_KEY;
    }
}
