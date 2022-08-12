package mate.academy.springboot.datajpa.repository.specification;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SpecificationProvider {
    public Specification<Product> getSpecification(List<String> params, String fieldName) {
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<String> inPredicate = criteriaBuilder.in(root.get(fieldName));
            params.forEach(inPredicate::value);
            return criteriaBuilder.and(inPredicate);
        };
    }
}
