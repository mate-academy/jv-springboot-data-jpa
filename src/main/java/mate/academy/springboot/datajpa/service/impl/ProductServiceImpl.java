package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
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
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Product by id " + id + " doesn't exist")
        );
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        Long id = product.getId();
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return productRepository.save(product);
        }
        throw new NoSuchElementException("Product by id " + id + " doesn't exist");
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal priceFrom, BigDecimal priceTo) {
        return productRepository.findAllByPriceBetween(priceFrom, priceTo);
    }

    @Override
    public List<Product> findAllByCategoryIds(List<Long> ids) {
        return productRepository.findAllByCategoryIdIn(ids);
    }
}
