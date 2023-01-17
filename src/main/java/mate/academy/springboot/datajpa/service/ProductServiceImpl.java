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
    private final ProductRepository repository;
    private final SpecificationManager<Product> specificationManager;

    public ProductServiceImpl(
            ProductRepository repository,
            SpecificationManager<Product> specificationManager
    ) {
        this.repository = repository;
        this.specificationManager = specificationManager;
    }

    @Override
    public Product createOrUpdate(Product product) {
        return repository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(getById(id));
    }

    @Override
    public List<Product> findAllBetweenPrice(BigDecimal priceFrom, BigDecimal priceTo) {
        return repository.findByPriceBetween(priceFrom, priceTo);
    }

    @Override
    public List<Product> findAll(Map<String, String> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Product> sp =
                    specificationManager.get(entry.getKey(), entry.getValue().split(","));
            specification = specification == null
                            ? Specification.where(sp)
                            : specification.and(sp);
        }
        return repository.findAll(specification);
    }
}
