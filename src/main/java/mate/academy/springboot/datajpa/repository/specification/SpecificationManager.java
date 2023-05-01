package mate.academy.springboot.datajpa.repository.specification;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationManager<T> {
    Specification<T> getSpecification(String filterKey, String[] params);
}
