package mate.academy.springboot.datajpa.specification.product;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.specification.SpecificationManager;
import mate.academy.springboot.datajpa.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecificationManager implements SpecificationManager<Product> {
    private final Map<String, SpecificationProvider<Product>> providersMap;

    public ProductSpecificationManager(List<SpecificationProvider<Product>> productSpecification) {
        this.providersMap = productSpecification.stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey,
                        Function.identity()));
    }

    @Override
    public Specification<Product> get(String filterkey, String[] params) {
        if (!providersMap.containsKey(filterkey)) {
            throw new RuntimeException("Key " + filterkey
                    + " is not supported for data filtering");
        }
        return providersMap.get(filterkey).getSpecification(params);
    }
}
