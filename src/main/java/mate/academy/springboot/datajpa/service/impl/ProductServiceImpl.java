package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException(String.format("Product with id: %s not found", id))
        );
    }

    @Override
    public Product update(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsBetweenPrice(BigDecimal from, BigDecimal to) {
        return productRepository.getProductsByPriceBetween(from, to);
    }

    @Override
    public List<Product> getProductsByCategories(List<String> categories) {
        return productRepository.getProductsByCategoryNameIn(categories);
    }
}
