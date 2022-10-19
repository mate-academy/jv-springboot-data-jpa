package mate.academy.springboot.datajpa.repository.specification.product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryInSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "category";
    private static final String FIELD_NAME = "category";
    private static final String FIELD_CATEGORY_NAME = "name";

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }

    @Override
    public Specification<Product> getSpecification(String[] categories) {
        return ((root, query, criteriaBuilder) -> {
            Join<Product, Category> join = root.join(FIELD_NAME, JoinType.INNER);
            CriteriaBuilder.In<String> predicate =
                    criteriaBuilder.in(join.get(FIELD_CATEGORY_NAME));
            for (String category: categories) {
                predicate.value(category);
            }
            return criteriaBuilder.and(predicate, predicate);
        });
    }
}