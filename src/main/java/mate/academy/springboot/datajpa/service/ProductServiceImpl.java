package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.stereotype.Repository;

@Repository
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
        return productRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        return productRepository.update(product.getPrice(),
                                        product.getTitle(),
                                        product.getCategory().getId(),
                                        product.getId());
    }

    @Override
    public Product getAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return getAllByPriceBetween(from, to);
    }
}
