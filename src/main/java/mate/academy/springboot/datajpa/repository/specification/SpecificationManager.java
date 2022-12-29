package mate.academy.springboot.datajpa.repository.specification;

import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationManager<T> {
    Specification<Product> get(String filterKey, String[] params);
}
