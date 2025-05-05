package mate.academy.springboot.datajpa.repository.specification.product;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationManager;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecificationManager<T> implements SpecificationManager<T> {
    private final Map<String, SpecificationProvider<Product>> providersMap;

    public ProductSpecificationManager(List<SpecificationProvider<Product>> productSpecification) {
        this.providersMap = productSpecification.stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey,
                        Function.identity()));
    }

    @Override
    public Specification<Product> get(String filterKey, String[] params) {
        if (!providersMap.containsKey(filterKey)) {
            throw new RuntimeException("Key " + filterKey
                    + " is not supported for data filtering.");
        }
        return providersMap.get(filterKey).getSpecification(params);
    }
}
