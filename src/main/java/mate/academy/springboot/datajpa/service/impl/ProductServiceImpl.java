package mate.academy.springboot.datajpa.service.impl;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if (optionalProduct.isPresent()) {
            Product oldProduct = optionalProduct.get();
            oldProduct.setPrice(product.getPrice());
            oldProduct.setTitle(product.getTitle());
            oldProduct.setCategory(product.getCategory());
            return productRepository.save(oldProduct);
        } else {
            throw new NoSuchElementException("There is no such product " + product);
        }
    }

    @Override
    public List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to) {

        return null;
    }

    @Override
    public List<Product> getAllByCategoriesIn(List<Category> categories) {
        return null;
    }
}
