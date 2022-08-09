package mate.academy.springboot.datajpa.repository.specification.product;

import java.math.BigDecimal;
import javax.persistence.criteria.Path;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceFromSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "priceFrom";
    private static final String FIELD_NAME = "price";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            Path<BigDecimal> pricePath = root.get(FIELD_NAME);
            BigDecimal priceParam = BigDecimal.valueOf(Double.parseDouble(params[0]));
            return criteriaBuilder.greaterThanOrEqualTo(pricePath, priceParam);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
