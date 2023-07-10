package mate.academy.springboot.datajpa.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("There is no product to update with id " + id));
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No product found with id " + id));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.getProductsByPriceBetween(from, to);
    }

    @Override
    public List<Product> getProductsByCategories(List<Long> categoriesId) {
        return productRepository.getProductsByCategoryIdIn(categoriesId);
    }
}
