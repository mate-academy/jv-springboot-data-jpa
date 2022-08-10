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
    private final Map<String, SpecificationProvider<Product>> providerMap;

    public ProductSpecificationManager(List<SpecificationProvider<Product>> providerList) {
        this.providerMap = providerList.stream()
                .collect(Collectors
                        .toMap(SpecificationProvider::getFilterKey,
                                Function.identity()));
    }

    @Override
    public Specification<Product> get(String filterKey, String[] params) {
        if (!providerMap.containsKey(filterKey)) {
            throw new RuntimeException("Can't create " + filterKey + " search criteria");
        }
        return providerMap.get(filterKey).getSpecification(params);
    }
}
