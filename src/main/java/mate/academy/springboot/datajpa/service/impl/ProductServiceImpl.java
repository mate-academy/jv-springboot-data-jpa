package mate.academy.springboot.datajpa.service.impl;

import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.repository.specification.SpecificationManager;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final SpecificationManager<Product> productSpecificationManager;
    private final ProductRepository productRepository;

    public ProductServiceImpl(SpecificationManager<Product> productSpecificationManager,
            ProductRepository productRepository) {
        this.productSpecificationManager = productSpecificationManager;
        this.productRepository = productRepository;
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't get user by ID: " + id));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllByParameters(Map<String, String> parameters) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            Specification<Product> productSpecification = productSpecificationManager
                    .get(entry.getKey(), entry.getValue().split(","));
            specification = specification == null
                            ? Specification.where(productSpecification)
                            : specification.and(productSpecification);
        }
        return productRepository.findAll(specification);
    }
}
