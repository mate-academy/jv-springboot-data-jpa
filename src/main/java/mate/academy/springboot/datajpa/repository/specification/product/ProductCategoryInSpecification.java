package mate.academy.springboot.datajpa.repository.specification.product;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryInSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "categoryIn";
    private static final String FIELD_CATEGORY_NAME = "category";
    private static final String FIELD_NAME = "name";

    @Override
    public Specification<Product> getSpecification(String[] categoryNames) {
        return (root, query, cb) -> {
            Join<Product, Category> join = root.join(FIELD_CATEGORY_NAME);
            CriteriaBuilder.In<String> predicate = cb.in(join.get(FIELD_NAME));
            for (String name : categoryNames) {
                predicate.value(name);
            }
            return cb.and(predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
