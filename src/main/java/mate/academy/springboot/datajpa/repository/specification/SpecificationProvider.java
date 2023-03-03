package mate.academy.springboot.datajpa.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
public interface SpecificationProvider<T> {
    Specification<T> getSpecification(String[] params);

    String getFilterKey();
}
