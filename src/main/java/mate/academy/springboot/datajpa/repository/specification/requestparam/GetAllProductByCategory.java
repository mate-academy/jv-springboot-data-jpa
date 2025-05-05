package mate.academy.springboot.datajpa.repository.specification.requestparam;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class GetAllProductByCategory implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "categoryId";
    private static final String FILTER_NAME = "categoryIn";
    private static final String JOIN_CRITERIA = "category";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, cb) -> {
            Join<Product, Category> products = root.join(JOIN_CRITERIA, JoinType.INNER);
            CriteriaBuilder.In<String> predicate = cb.in(products.get(FILTER_NAME));
            for (String evaluation : params) {
                predicate.value(evaluation);
            }
            return cb.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
