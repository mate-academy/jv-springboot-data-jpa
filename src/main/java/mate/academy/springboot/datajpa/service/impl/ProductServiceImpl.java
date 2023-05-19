package mate.academy.springboot.datajpa.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No product with id: " + id));
    }

    @Override
    public Product update(Product product) {
        productRepository.findById(product.getId()).orElseThrow(
                () -> new EntityNotFoundException("There are no product with id: "
                        + product.getId() + " to update"));
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> findAllByCategoryIn(List<String> categories) {
        return productRepository.findAllByCategoryNameIn(categories);
    }
}
