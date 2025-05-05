package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.repository.specification.SpecificationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final SpecificationManager<Product> productSpecificationManager;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(SpecificationManager<Product> productSpecificationManager,
                              ProductRepository productRepository) {
        this.productSpecificationManager = productSpecificationManager;
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found! Params: id = " + id)
        );
    }

    @Override
    public Product deleteById(Long id) {
        Product productForDeletion = productRepository.getById(id);
        productRepository.deleteById(id);
        return productForDeletion;
    }

    @Override
    public Product update(Long id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllByPriceBetween(BigDecimal priceStartsWith, BigDecimal priceUpTo) {
        return productRepository.findAllByPriceBetween(priceStartsWith, priceUpTo);
    }

    @Override
    public List<Product> getAllProductsWithCategories(Map<String, String> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Product> spec = productSpecificationManager.get(entry.getKey(),
                    entry.getValue().split(","));
            specification = specification == null ? Specification.where(spec)
                    : specification.and(spec);
        }
        return productRepository.findAll(specification);
    }
}
