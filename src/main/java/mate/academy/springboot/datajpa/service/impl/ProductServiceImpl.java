package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Component;
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
    public Product get(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can not get product by id " + id));
    }

    @Override
    public void delete(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } throw new NoSuchElementException("Can not delete product by id " + id);
    }

    @Override
    public Product update(Product product) {
        if (productRepository.existsById(product.getId())) {
            return productRepository.save(product);
        } throw new NoSuchElementException("Can not update product " + product.getTitle());
    }

    @Override
    public List<Product> getAllBetweenPrice(BigDecimal from, BigDecimal to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> findAllByCategoryIn(List<Category> categories) {
        return productRepository.findAllByCategoryIn(categories);
    }


}
