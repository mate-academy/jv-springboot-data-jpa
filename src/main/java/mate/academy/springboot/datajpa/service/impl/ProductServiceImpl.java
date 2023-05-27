package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dao.ProductRepository;
import mate.academy.springboot.datajpa.model.Product;
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
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Product with id " + id + " hasn't found"));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new NoSuchElementException("Product with id " + id + " hasn't found");
        }
        Product productFormDb = productOptional.get();
        productFormDb.setTitle(product.getTitle());
        productFormDb.setPrice(product.getPrice());
        productFormDb.setCategory(product.getCategory());
        return productRepository.save(productFormDb);
    }

    @Override
    public List<Product> getAllBetweenPrice(BigDecimal from, BigDecimal to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> getAllByCategories(List<String> categories) {
        return productRepository.findByCategoryNameIn(categories);
    }
}
