package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
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
    private static final String COMMA = ",";
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
        return productRepository.getReferenceById(id);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.findAllByPriceBetween(from,to);
    }

    @Override
    public List<Product> findAll(Map<String, String> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Product> sp = specificationManager.get(entry.getKey(),
                    entry.getValue().split(COMMA));
            specification = specification == null ? Specification.where(sp) : specification.and(sp);
        }
        return productRepository.findAll(specification);
    }
}
