package mate.academy.springboot.datajpa.service;

import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't find product by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        productRepository.findById(product.getId()).orElseThrow(() ->
                new EntityNotFoundException("Can't find product by id: " + product.getId()));
        return productRepository.save(product);
    }

    @Override
    public List<Product> findByCategoryNameIn(List<String> categories) {
        return productRepository.findByCategoryNameIn(categories);
    }
}
