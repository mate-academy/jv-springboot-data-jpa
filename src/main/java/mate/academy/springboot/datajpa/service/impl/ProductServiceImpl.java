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
    private ProductRepository productRepository;

    @Override
    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public Product find(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No such product with id: "
                        + id + " exists"));
    }

    @Override
    public List<Product> findProductByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.findProductByPriceBetween(from, to);
    }

    @Override
    public List<Product> findAll(List<String> categories) {
        return productRepository.findProductsByCategoryNameIn(categories);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void update(Product entity) {
        productRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
