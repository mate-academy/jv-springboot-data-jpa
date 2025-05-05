package mate.academy.springboot.datajpa.repository.specification;

import static java.util.function.Function.identity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.exception.UnsupportedFilterKeyException;
import org.springframework.data.jpa.domain.Specification;

public abstract class AbstractSpecificationManager<T> implements SpecificationManager<T> {
    private final Map<String, SpecificationProvider<T>> providers;
    private final Class<T> clazz;

    protected AbstractSpecificationManager(List<SpecificationProvider<T>> specificationProviders,
                                           Class<T> clazz) {
        this.clazz = clazz;
        providers = specificationProviders.stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey, identity()));
    }

    @Override
    public Specification<T> get(String filterKey, String[] params) {
        if (!providers.containsKey(filterKey)) {
            throw UnsupportedFilterKeyException.withKey(filterKey, clazz);
        }
        return providers.get(filterKey).getSpecification(params);
    }
}
