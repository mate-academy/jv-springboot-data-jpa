package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find product by id " + id));
    }

    @Override
    public boolean delete(Product product) {
        productRepository.delete(product);
        return true;
    }

    @Transactional
    @Override
    public Product update(Product product) {
        productRepository.update(product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getCategory()
        );
        return product;
    }

    @Override
    public List<Product> getByPriceBetween(Long from, Long to) {
        return productRepository.getProductsByPriceBetween(from, to);
    }

    @Override
    public List<Product> getByCategory(Category category) {
        return productRepository.getProductsByCategory(category.getId());
    }
}
