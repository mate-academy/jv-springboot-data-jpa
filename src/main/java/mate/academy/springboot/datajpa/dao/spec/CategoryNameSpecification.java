package mate.academy.springboot.datajpa.dao.spec;

import javax.persistence.criteria.CriteriaBuilder;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CategoryNameSpecification implements SpecificationProvider<Category> {
    private static final String FILTER_KEY = "nameIn";
    private static final String FIELD_NAME = "name";

    @Override
    public Specification<Category> getSpecification(String[] params) {
        return (root, query, cb) -> {
            CriteriaBuilder.In<String> predicate = cb.in(root.get(FIELD_NAME));
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