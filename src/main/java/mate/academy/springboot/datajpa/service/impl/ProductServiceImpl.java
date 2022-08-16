package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.repository.specification.SpecificationManager;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SpecificationManager<Product> productSpecificationManager;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              SpecificationManager<Product> productSpecificationManager) {
        this.productRepository = productRepository;
        this.productSpecificationManager = productSpecificationManager;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllWherePriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findAllByPriceBetweenOrderByPriceAsc(minPrice, maxPrice);
    }

    @Override
    public List<Product> findAllByParams(Map<String, String> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Product> productSpecification
                    = productSpecificationManager.get(entry.getKey(),
                    entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(productSpecification)
                    : specification.and(productSpecification);
        }
        return productRepository.findAll(specification);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
