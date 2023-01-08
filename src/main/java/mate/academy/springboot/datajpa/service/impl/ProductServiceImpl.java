package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import mate.academy.springboot.datajpa.model.Category;
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
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't get a product by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.getAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> getAllByCategories(List<Category> categories) {
        List<Product> products = new ArrayList<>();
        for (Category category : categories) {
            products.addAll(productRepository.getAllByCategory(category));
        }
        return products;
    }
}
