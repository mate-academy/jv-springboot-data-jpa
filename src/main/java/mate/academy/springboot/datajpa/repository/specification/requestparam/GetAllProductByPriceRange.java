package mate.academy.springboot.datajpa.repository.specification.requestparam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class GetAllProductByPriceRange implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "priceRange";
    private static final String FILTER_NAME = "price";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Specification<Product> getSpecification(String[] params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        double fromPrice = Double.parseDouble(params[0]);
        double toPrice = Double.parseDouble(params[1]);
        return (root, query, criteriaBuilder) ->
                cb.between(root.get(FILTER_NAME), fromPrice, toPrice);
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
