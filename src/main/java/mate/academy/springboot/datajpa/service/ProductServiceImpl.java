package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(productRepository.getById(id));
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllBetween(BigDecimal from, BigDecimal to) {
        return productRepository.getAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> getAllByCategoryIdIn(List<Long> categories) {
        return productRepository.getAllByCategoryIdIn(categories);
    }
}
