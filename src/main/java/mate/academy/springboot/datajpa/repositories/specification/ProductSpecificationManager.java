package mate.academy.springboot.datajpa.repositories.specification;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecificationManager implements
        SpecificationManager<Product> {
    private final Map<String, SpecificationProvider<Product>> providersMap;

    @Autowired
    public ProductSpecificationManager(List<SpecificationProvider<Product>> productSpecifications) {
        this.providersMap = productSpecifications.stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey,
                        Function.identity()));
    }

    @Override
    public Specification<Product> get(String filterKey, String[] params) {
        if (!providersMap.containsKey(filterKey)) {
            throw new RuntimeException("You couldn't filter by this key " + filterKey);
        }
        return providersMap.get(filterKey).getSpecification(params);
    }
}
