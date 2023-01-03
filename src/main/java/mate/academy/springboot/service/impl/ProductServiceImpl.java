package mate.academy.springboot.service.impl;

import java.util.List;
import mate.academy.springboot.model.Category;
import mate.academy.springboot.model.Product;
import mate.academy.springboot.repository.ProductRepository;
import mate.academy.springboot.service.ProductService;
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
        return productRepository.getReferenceById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllBetweenPrice(Double from, Double to) {
        return productRepository.findProductsByPriceBetween(from, to);
    }

    @Override
    public List<Product> getAllByCategory(Category category) {
        return productRepository.findProductsByCategory(category);
    }
}
