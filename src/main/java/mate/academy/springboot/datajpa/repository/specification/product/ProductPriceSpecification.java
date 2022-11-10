package mate.academy.springboot.datajpa.repository.specification.product;

import java.math.BigDecimal;
import javax.persistence.criteria.CriteriaBuilder;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class ProductPriceSpecification implements SpecificationProvider<Product> {
    public static final String FILTER_KEY = "priceBetween";
    public static final String FIELD_NAME = "price";
    public static final int FROM_PRICE_INDEX = 0;
    public static final int TO_PRICE_INDEX = 1;

    @Override
    public Specification<Product> getSpecification(String[] categories) {
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(root.get(FIELD_NAME));
            String[] priceBetween = categories[0].split("-");
            BigDecimal fromPrice = new BigDecimal(priceBetween[FROM_PRICE_INDEX]);
            BigDecimal toPrice = new BigDecimal(priceBetween[TO_PRICE_INDEX]);
            return criteriaBuilder.between(root.get(FIELD_NAME), fromPrice, toPrice);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
