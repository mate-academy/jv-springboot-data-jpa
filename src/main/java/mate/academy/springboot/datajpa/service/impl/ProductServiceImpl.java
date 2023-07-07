package mate.academy.springboot.datajpa.service.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.exception.DbException;
import mate.academy.springboot.datajpa.model.entity.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new DbException(
                        "Product id #" + id + " absent in database"));
    }

    @Override
    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new DbException(
                    "Can`t delete product id #" + id + " from database. No such product");
        }
    }

    @Override
    public Product update(Product product) {
        if (productRepository.findById(product.getId()).isPresent()) {
            return productRepository.save(product);
        }
        throw new DbException(
                "Can`t update product id #" + product.getId()
                        + " in database. No such product");
    }

    @Override
    public List<Product> findAllWherePriceBetween(double from, double to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> findAllByCategoryNameIn(List<String> categories) {
        return productRepository.findAllByCategoryNameIn(categories);
    }
}
