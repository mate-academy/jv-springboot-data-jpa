package mate.academy.springboot.datajpa.service.specification;

import java.util.Map;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GenerateProductSpecificationImpl implements GenerateSpecification<Product> {
    private final SpecificationManager<Product> autoSpecificationManager;

    @Override
    public Specification<Product> generate(Map<String, String> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry: params.entrySet()) {
            Specification<Product> spec = autoSpecificationManager.get(entry.getKey(),
                    entry.getValue().split(","));
            specification = specification == null ? Specification.where(spec)
                    : specification.and(spec);
        }
        return specification;
    }
}
