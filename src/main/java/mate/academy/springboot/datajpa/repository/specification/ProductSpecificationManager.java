package mate.academy.springboot.datajpa.repository.specification;

import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecificationManager extends AbstractSpecificationManager<Product> {
    public ProductSpecificationManager(List<SpecificationProvider<Product>> providers) {
        super(providers, Product.class);
    }
}
