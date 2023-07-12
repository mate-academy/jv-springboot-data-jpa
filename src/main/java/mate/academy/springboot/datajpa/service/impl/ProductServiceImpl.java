package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.exception.DataProcessingException;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new DataProcessingException(
                        "Product for id: %d not found".formatted(productId)));
    }

    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }

    public Product update(Product product) {
        productRepository.findById(product.getId()).orElseThrow(() -> new DataProcessingException(
                        "Couldn't find original product to update, consider creating a new one."));
        return productRepository.save(product);
    }

    @Override
    public List<Product> findByPriceBetween(BigDecimal priceFrom, BigDecimal priceTo) {
        return productRepository.findAllByPriceBetween(priceFrom, priceTo);
    }

    @Override
    public List<Product> findByCategories(List<String> categories) {
        return productRepository.findAllByCategoryNameIn(categories);
    }

}
