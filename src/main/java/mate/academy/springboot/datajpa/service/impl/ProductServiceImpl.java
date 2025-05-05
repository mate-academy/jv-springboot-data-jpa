package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.specification.SpecificationManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SpecificationManager<Product> specificationManager;

    public ProductServiceImpl(ProductRepository productRepository,
                              SpecificationManager<Product> specificationManager) {
        this.productRepository = productRepository;
        this.specificationManager = specificationManager;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("There is no product with id: " + id)
        );
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> findAllBy(Map<String, String> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Product> productSpecification
                    = specificationManager.get(entry.getKey(), entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(productSpecification)
                    : specification.and(productSpecification);
        }
        return productRepository.findAll(specification);
    }
}
