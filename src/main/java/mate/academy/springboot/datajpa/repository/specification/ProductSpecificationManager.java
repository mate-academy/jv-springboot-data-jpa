package mate.academy.springboot.datajpa.repository.specification;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecificationManager implements SpecificationManager<Product> {
    private final Map<String, SpecificationProvider<Product>> providerMap;

    @Autowired
    public ProductSpecificationManager(List<SpecificationProvider<Product>> productSpecifications) {
        this.providerMap = productSpecifications.stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey, i -> i));
    }

    @Override
    public Specification<Product> get(String filterKey, String[] params) {
        if (! providerMap.containsKey(filterKey)) {
            throw new RuntimeException("Key " + filterKey + " is not supported.");
        }
        return providerMap.get(filterKey).getSpecification(params);
    }
}
