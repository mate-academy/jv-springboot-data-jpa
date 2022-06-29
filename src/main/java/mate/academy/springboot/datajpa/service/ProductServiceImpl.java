package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
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
        return productRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(productRepository.getById(id));
    }

    @Override
    public Product update(Product product) {
        return productRepository.updateProductById(product.getTitle(), product.getPrice(),
                product.getCategory(), product.getId());
    }

    @Override
    public List<Product> getByPrice(BigDecimal lowerBound, BigDecimal upperBound) {
        return productRepository.findAllByPriceBetween(lowerBound, upperBound);
    }

    @Override
    public List<Product> getByCategory(List<Long> categoryIds) {
        return productRepository.getAllByCategory(categoryIds);
    }
}
