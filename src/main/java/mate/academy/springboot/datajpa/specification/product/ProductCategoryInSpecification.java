package mate.academy.springboot.datajpa.specification.product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryInSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "category";
    private static final String FILED_NAME = "name";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, cb) -> {
            Join<Object, Object> join = root.join("category", JoinType.INNER);
            CriteriaBuilder.In<String> predicate = cb.in(join.get(FILED_NAME));
            for (String param : params) {
                predicate.value(param);
            }
            return cb.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
