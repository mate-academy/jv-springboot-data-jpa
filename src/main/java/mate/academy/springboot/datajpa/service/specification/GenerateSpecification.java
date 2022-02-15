package mate.academy.springboot.datajpa.service.specification;

import java.util.Map;
import org.springframework.data.jpa.domain.Specification;

public interface GenerateSpecification<T> {
    Specification<T> generate(Map<String, String> params);
}
