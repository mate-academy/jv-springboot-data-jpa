package mate.academy.springboot.datajpa.repository.specification.product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationManager;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecificationManagerImpl implements SpecificationManager<Product> {
    private Map<String, SpecificationProvider<Product>> providersMap;

    public ProductSpecificationManagerImpl(List<SpecificationProvider<Product>> providersList) {
        this.providersMap = providersList.stream()
                .collect(Collectors.toMap(
                        SpecificationProvider::getFilterKey,
                        Function.identity()));
    }

    @Override
    public Specification<Product> get(String filterKey, String[] params) {
        return Optional.ofNullable(providersMap.get(filterKey))
                .orElseThrow(() ->
                        new RuntimeException("Strategy for product provider is "
                                + "not supported with filterKey=" + filterKey))
                .getSpecification(params);
    }
}
