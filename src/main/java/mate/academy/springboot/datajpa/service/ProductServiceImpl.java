package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.repository.specification.SpecificationManager;
import mate.academy.springboot.datajpa.repository.specification.product.ProductCategoryInSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SpecificationManager<Product> productSpecificationManager;
    private final ProductCategoryInSpecification categoryInSpecification;

    public ProductServiceImpl(ProductRepository productRepository,
                              SpecificationManager<Product> productSpecificationManager,
                              ProductCategoryInSpecification categoryInSpecification) {
        this.productRepository = productRepository;
        this.productSpecificationManager = productSpecificationManager;
        this.categoryInSpecification = categoryInSpecification;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> findAllByCategories(List<String> categories) {
        Specification<Product> specification = null;
        for (String category : categories) {
            Specification<Product> sp = productSpecificationManager
                    .get(categoryInSpecification.getFilterKey(),
                    category);
            specification = specification == null
                    ? Specification.where(sp) : specification.or(sp);
        }
        return productRepository.findAll(specification);
    }
}
