package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There's no product with id " + id));
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("There's no product with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllBetweenTwoPrices(BigDecimal from, BigDecimal to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> getAllInCategories(List<String> categoryNames) {
        return productRepository.findProductsByCategoryNameIn(categoryNames);
    }
}
