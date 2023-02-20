package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.repository.specification.SpecificationManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    private final SpecificationManager<Product> productSpecificationManager;
    private final ProductRepository repository;

    public ProductServiceImpl(SpecificationManager<Product> productSpecificationManager,
                              ProductRepository repository) {
        this.productSpecificationManager = productSpecificationManager;
        this.repository = repository;
    }

    @Override
    public Product add(Product product) {
        return repository.save(product);
    }

    @Transactional
    @Override
    public void update(Product product) {
        repository.updateProduct(product.getId(), product.getTitle(),
                product.getPrice(), product.getCategory().getId());
    }

    @Override
    public Product get(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findAll(Map<String, String> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Product> sp = productSpecificationManager.get(entry.getKey(),
                    entry.getValue().split(","));
            specification = specification == null ? Specification.where(sp) : specification.and(sp);
        }
        return repository.findAll(specification);
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal priceFrom, BigDecimal priceTo) {
        return repository.findAllByPriceBetween(priceFrom, priceTo);
    }
}
