package mate.academy.springboot.datajpa.specification;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationManager<T> {
    Specification<T> getSpecification(String filterKey, String[] value);
}
