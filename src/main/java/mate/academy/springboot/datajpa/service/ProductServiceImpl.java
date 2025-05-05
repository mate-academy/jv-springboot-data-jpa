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
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllWherePriceBetween(BigDecimal priceMin, BigDecimal priceMax) {
        if (priceMin.compareTo(priceMax) == 1) {
            BigDecimal buf = priceMin;
            priceMin = priceMax;
            priceMax = buf;
        }
        return productRepository.findAllByPriceBetween(priceMin, priceMax);
    }

    @Override
    public List<Product> getAllByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }
}
