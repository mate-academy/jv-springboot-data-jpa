package mate.academy.springboot.datajpa.service.h2;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.repository.specification.SpecificationManager;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SpecificationManager<Product> productSpecificationManager;

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> getAll(Map<String, String> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> pair : params.entrySet()) {
            Specification<Product> sp = productSpecificationManager.get(
                    pair.getKey(),
                    pair.getValue().split(","));
            specification = specification == null
                ? Specification.where(sp) : specification.and(sp);
        }
        return productRepository.findAll(specification);
    }
}
