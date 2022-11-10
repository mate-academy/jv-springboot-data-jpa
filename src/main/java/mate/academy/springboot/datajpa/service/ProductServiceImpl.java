package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.repository.specification.SpecificationManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SpecificationManager<Product> productSpManager;

    public ProductServiceImpl(ProductRepository productRepository,
            CategoryRepository categoryRepository,
            SpecificationManager<Product> productSpManager) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productSpManager = productSpManager;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public Product update(Product product, Long categoryId) {
        Category categoryFromDB = categoryRepository.getById(categoryId);
        Product productFromDB = productRepository.getById(product.getId());
        productFromDB.setCategory(categoryFromDB);
        productFromDB.setTitle(product.getTitle());
        productFromDB.setPrice(product.getPrice());
        return productRepository.saveAndFlush(productFromDB);
    }

    @Override
    public boolean remove(Long id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Product> findAll(Map<String, String> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Product> productSpecification = productSpManager.get(entry.getKey(),
                    entry.getValue().split(","));
            specification = specification == null
                  ? Specification.where(productSpecification) :
                  specification.and(productSpecification);
        }
        return productRepository.findAll(specification);
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.findAllByPriceBetween(from, to);
    }
}
