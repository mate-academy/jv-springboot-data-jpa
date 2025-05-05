package mate.academy.springboot.datajpa.specification;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecificationManager implements SpecificationManager<Product> {
    private final Map<String, SpecificationProvider<Product>> providersMap;

    public ProductSpecificationManager(List<SpecificationProvider<Product>>
                                               productSpecifications) {
        this.providersMap = productSpecifications
                .stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey,
                        Function.identity()));
    }

    @Override
    public Specification<Product> get(String filterKey, String[] params) {
        if (!providersMap.containsKey(filterKey)) {
            throw new RuntimeException("No filter with such filterKey " + filterKey + " was found");
        }
        return providersMap.get(filterKey).getSpecification(params);
    }
}

