package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;


    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {

        return productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find product by id:" + id));
    }

    @Override
    public void deletedById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to) {

        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> findAllByCategoryIn(List<Long> categoryIds) {
        List<Category> categories = new ArrayList<>();
        for (Long id : categoryIds) {
            categories.add(categoryService.getCategoryById(id));
        }
        return productRepository.findAllByCategoryIn(categories);
    }
}

