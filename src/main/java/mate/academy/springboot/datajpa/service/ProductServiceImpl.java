package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryService categoryService) {
        this.productRepository = productRepository;
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
    public List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> findAllByCategories(List<Long> categoryIds) {
        return productRepository.findAllByCategoryIn(categoryIds
                .stream()
                .map(categoryService::getById)
                .collect(Collectors.toList()));
    }
}
