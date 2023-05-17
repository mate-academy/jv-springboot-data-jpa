package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
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
        return productRepository.getReferenceById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        Product productFromDb = productRepository.getReferenceById(product.getId());
        productFromDb.setTitle(product.getTitle());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setCategory(product.getCategory());
        return productRepository.save(productFromDb);
    }

    @Override
    public List<Product> findProductsByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.findProductsByPriceBetween(from, to);
    }

    @Override
    public List<Product> findProductsByCategoryIn(List<Category> categories) {
        return productRepository.findProductsByCategoryIn(categories);
    }

}
