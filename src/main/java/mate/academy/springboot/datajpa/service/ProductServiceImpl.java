package mate.academy.springboot.datajpa.service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.repository.specification.ProductSpecificationManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductSpecificationManager productSpecificationManager;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductSpecificationManager productSpecificationManager,
                              ProductRepository productRepository) {
        this.productSpecificationManager = productSpecificationManager;
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Product with id " + id + "is not exist at DB"));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAll(Map<String, String> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Product> ps = productSpecificationManager.get(
                    entry.getKey(),
                    entry.getValue().split(","));
            specification = specification == null ? Specification.where(ps) : specification.and(ps);
        }
        return productRepository.findAll(specification);
    }
}
