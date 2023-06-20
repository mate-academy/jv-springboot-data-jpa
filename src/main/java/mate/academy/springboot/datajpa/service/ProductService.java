package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(Product product) {
        productRepository.save(product);
    }

    public Product getById(Long id) {
        return productRepository.getReferenceById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void update(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllWherePriceBetween(BigDecimal lowest,
                                                 BigDecimal highest) {
        return productRepository.findAllByPriceBetween(lowest, highest);
    }

    public List<Product> getAllInCategories(List<String> categories) {
        return productRepository.findAllByCategoryIn(categories);
    }
}
