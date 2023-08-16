package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can't find product with id " + id));
    }

    @Override
    public Product update(Long id, Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product can't be null");
        }
        Product productFromDb = productRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can't find product with id " + id));
        productFromDb.setCategory(product.getCategory());
        productFromDb.setTitle(product.getTitle());
        productFromDb.setPrice(product.getPrice());
        return productRepository.save(productFromDb);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllByPriceBetween(BigDecimal priceFrom, BigDecimal priceTo) {
        return productRepository.getAllByPriceBetween(priceFrom, priceTo);
    }

    @Override
    public List<Product> getAllByCategoryIds(Set<Long> categoryIds) {
        return productRepository.getAllByCategoryIdIn(categoryIds);
    }
}
