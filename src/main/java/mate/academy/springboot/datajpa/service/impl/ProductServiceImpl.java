package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find product with id: " + id)
        );
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllByPriceRange(BigDecimal from, BigDecimal to) {
        return productRepository.findAllByPriceIsBetween(from, to);
    }

    @Override
    public List<Product> getAllByCategories(List<String> categoryNames) {
        List<Category> categories = categoryNames.stream().map(
                categoryName -> categoryRepository.findByName(categoryName).orElseThrow(
                        () -> new RuntimeException("Can't find category: " + categoryName)))
                .collect(Collectors.toList());
        return productRepository.findAllByCategoryIn(categories);
    }
}
