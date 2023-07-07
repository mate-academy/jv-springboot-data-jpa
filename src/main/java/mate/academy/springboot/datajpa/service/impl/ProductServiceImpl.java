package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
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
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException("Can't get product by id: " + id);
        } else {
            return productRepository.getById(id);
        }
    }

    @Override
    public Product update(Product product) {
        if (productRepository.existsById(product.getId())) {
            return productRepository.save(product);
        } else {
            throw new NoSuchElementException("Can't update product: " + product);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Can't delete product with id: " + id);
        }
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.findAllByPriceBetween(from, to);
    }
}
