package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository repository,
                              CategoryService categoryService) {
        this.productRepository = repository;
        this.categoryService = categoryService;
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
    public List<Product> findAllByPriceBetween(BigDecimal fromPrice, BigDecimal toPrice) {
        return productRepository.findAllByPriceBetween(fromPrice, toPrice);
    }

    @Override
    public List<Product> findAllByCategoryIn(String categoryIds) {
        return productRepository.findAllByCategoryIn(getCategories(categoryIds));
    }

    private Set<Category> getCategories(String categoryIds) {
        return Arrays.stream(categoryIds.split(","))
                .map(Long::valueOf)
                .map(categoryService::getById)
                .collect(Collectors.toSet());
    }
}
