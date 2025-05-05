package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product add(Product model) {
        return productRepository.save(model);
    }

    @Override
    public Product get(Long id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    public Product update(Product model) {
        return productRepository.save(model);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllBetweenPrice(BigDecimal from, BigDecimal to) {
        return productRepository.getAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> getAllInCategories(List<Category> categories) {
        return productRepository.getAllByCategoryIn(categories);
    }
}
