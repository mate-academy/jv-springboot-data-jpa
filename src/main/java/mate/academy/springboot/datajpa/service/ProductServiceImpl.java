package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.repository.specification.SpecificationManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    public static final String SPECIFICATION_DELIMITER = ",";
    private final ProductRepository productRepository;
    private final SpecificationManager<Product> specificationManager;

    public ProductServiceImpl(ProductRepository productRepository,
                              SpecificationManager<Product> specificationManager) {
        this.productRepository = productRepository;
        this.specificationManager = specificationManager;
    }

    @Override
    public Product get(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll(Map<String, String> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Product> sp = specificationManager.get(entry.getKey(),
                    entry.getValue().split(SPECIFICATION_DELIMITER));
            specification = specification == null ? Specification.where(sp) : specification.and(sp);
        }
        return productRepository.findAll(specification);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(get(id));
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.getProductsByPriceIsBetween(from, to);
    }
}
