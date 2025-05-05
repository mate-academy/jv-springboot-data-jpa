package mate.academy.springboot.datajpa.repository.specification;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<T> {
    String getFilterKey();

    Specification<T> getSpecification(String value);
}
