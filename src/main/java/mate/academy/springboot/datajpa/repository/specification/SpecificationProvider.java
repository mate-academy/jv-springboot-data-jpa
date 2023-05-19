package mate.academy.springboot.datajpa.repository.specification;

import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<T> {
    Specification<Product> getSpecification(String[] params);

    String getFilterKey();
}
