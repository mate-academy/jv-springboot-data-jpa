package mate.academy.springboot.datajpa.repository.specification.product;

import javax.persistence.criteria.CriteriaBuilder;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class ProductPriceSpecification implements SpecificationProvider<Product> {
    public static final String FILTER_KEY = "priceBetween";
    public static final String FIELD_NAME = "price";

    @Override
    public Specification<Product> getSpecification(String[] categories) {
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(root.get(FIELD_NAME));
            int from = Integer.parseInt(categories[0].split("-")[0]);
            int to = Integer.parseInt(categories[0].split("-")[1]);
            return criteriaBuilder.between(root.get(FIELD_NAME), from, to);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
