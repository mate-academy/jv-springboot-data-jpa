package repository.specification;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecificationManager implements SpecificationManager<Product> {
    private final Map<String, SpecificationProvider<Product>> providerMap;

    public ProductSpecificationManager(List<SpecificationProvider<Product>> productSpecifications) {
        this.providerMap = productSpecifications.stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey,
                        Function.identity()));
    }

    @Override
    public Specification<Product> get(String filterKey, String[] params) {
        if (!providerMap.containsKey(filterKey)) {
            throw new RuntimeException("Key " + filterKey + " is not supported");
        }
        return providerMap.get(filterKey).getSpecification(params);
    }
}
