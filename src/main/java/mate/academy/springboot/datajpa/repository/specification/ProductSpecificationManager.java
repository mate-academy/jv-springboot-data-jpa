package mate.academy.springboot.datajpa.repository.specification;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecificationManager implements SpecificationManager<Product> {
    private final Map<String, SpecificationProvider<Product>> specificationProviderMap;

    public ProductSpecificationManager(List<SpecificationProvider<Product>> productSpecifications) {
        this.specificationProviderMap = productSpecifications.stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey,
                        Function.identity()));
    }

    @Override
    public Specification<Product> get(String key, String value) {
        if (!specificationProviderMap.containsKey(key)) {
            throw new RuntimeException("Key " + key + "is not supported for data filtering!");
        }
        return specificationProviderMap.get(key).getSpecification(value);
    }
}
