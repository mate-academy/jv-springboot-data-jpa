package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found! Params: id = " + id)
        );
    }

    @Override
    public Product deleteById(Long id) {
        Product productForDeletion = productRepository.getById(id);
        productRepository.deleteById(id);
        return productForDeletion;
    }

    @Override
    public Product update(Long id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllByPriceBetween(BigDecimal priceStartsWith, BigDecimal priceUpTo) {
        return productRepository.findAllByPriceBetween(priceStartsWith, priceUpTo);
    }

    @Override
    public List<Product> getAllProductsWithCategories(List<Category> categories) {
        return productRepository.findAllByCategoryContains(categories);
    }
}
