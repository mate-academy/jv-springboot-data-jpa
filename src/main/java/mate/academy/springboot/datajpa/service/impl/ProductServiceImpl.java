package mate.academy.springboot.datajpa.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.repository.specification.SpecificationManager;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String REGEX = ",";
    private final ProductRepository productRepository;
    private final SpecificationManager<Product> productSpecificationManager;

    public ProductServiceImpl(
            ProductRepository productRepository,
            SpecificationManager<Product> productSpecificationManager) {
        this.productRepository = productRepository;
        this.productSpecificationManager = productSpecificationManager;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Couldn't get product by id:" + id));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        Product productFromDb = getById(product.getId());
        productFromDb.setTitle(product.getTitle());
        productFromDb.setCategory(product.getCategory());
        productFromDb.setPrice(product.getPrice());
        return productRepository.save(productFromDb);
    }

    @Override
    public List<Product> findAllByPriceBetween(Integer from, Integer to) {
        return productRepository.findProductsByPriceBetween(from, to);
    }

    @Override
    public List<Product> findAllByCategories(Map<String, String> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> category : params.entrySet()) {
            Specification<Product> sp = productSpecificationManager.get(category.getKey(),
                    category.getValue().split(REGEX));
            specification = specification == null
                    ? Specification.where(sp) : specification.and(sp);
        }
        return productRepository.findAll(specification);
    }
}
