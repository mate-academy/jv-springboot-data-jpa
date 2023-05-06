package mate.academy.springboot.datajpa.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
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
    public Product get(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't get product by id: " + id));
    }

    @Override
    public Product update(Product product) {
        Optional<Product> byId = productRepository.findById(product.getId());
        if (byId.isPresent()) {
            Product update = byId.get();
            update.setCategory(product.getCategory());
            update.setTitle(product.getTitle());
            update.setPrice(product.getPrice());
            return productRepository.save(update);
        } else {
            throw new EntityNotFoundException("Can't get product by id: " + product.getId());
        }
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> getByCategory(List<String> categories) {
        return productRepository.getProductByCategoryIn(categories);
    }
}
