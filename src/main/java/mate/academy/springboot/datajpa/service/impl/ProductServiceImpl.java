package mate.academy.springboot.datajpa.service.impl;

import jakarta.persistence.EntityNotFoundException;
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
    public Product get(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No product found with id " + id));
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Long id, Product product) {
        productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No product to update with id " + id));
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getByPriceBetween(Long minPrice, Long maxPrice) {
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Product> getByCategoryIn(List<String> categoryNames) {
        return productRepository.findAllByCategoryNameIn(categoryNames);
    }
}
