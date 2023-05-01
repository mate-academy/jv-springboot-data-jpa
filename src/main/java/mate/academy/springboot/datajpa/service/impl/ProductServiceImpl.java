package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
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
        return productRepository.findById(id).get();
    }

    @Override
    public Product update(Product product) {
        Product productFromDb = productRepository.findById(product.getId()).get();
        productFromDb.setId(product.getId());
        productFromDb.setTitle(product.getTitle());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setCategory(product.getCategory());
        return productRepository.save(productFromDb);
    }

    @Override
    public List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.getAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> getAllByCategories(List<String> categories) {
        return productRepository.findAllByCategoryNameIn(categories);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
