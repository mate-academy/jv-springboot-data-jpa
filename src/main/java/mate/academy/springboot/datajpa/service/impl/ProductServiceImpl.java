package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findProductsByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.findProductsByPriceBetween(from, to);
    }

    @Override
    public List<Product> findProductsByCategoryNameIn(List<String> categories) {
        return productRepository.findProductsByCategoryNameIn(categories);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find product by id " + id));
    }

    @Override
    public void delete(Long id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new RuntimeException(("Can't find product by id " + id + " for delete"));
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        if (productRepository.findById(product.getId()).isPresent()) {
            return productRepository.saveAndFlush(product);
        }
        throw new RuntimeException(("Can't find product by id " + product.getId()
                + " for update"));
    }
}
