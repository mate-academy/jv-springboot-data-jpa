package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Can`t find product with id: " + id));
    }

    @Override
    public Product update(Long id, Product product) {
        findById(id);
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAllBetweenPrice(BigDecimal from, BigDecimal to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> findInCategories(List<String> categories) {
        return productRepository.findAllByCategory_NameIn(categories);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
