package mate.academy.springboot.datajpa.repository.specification.product;

import javax.persistence.criteria.CriteriaBuilder;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductTitleInSpecificationProvider implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "titleIn";
    private static final String FIELD_NAME = "title";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return ((root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(root.get(FIELD_NAME));
            for (String param : params) {
                predicate.value(param);
            }
            return criteriaBuilder.and(predicate, predicate);
        });
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
