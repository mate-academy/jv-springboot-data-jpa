package mate.academy.springboot.datajpa.service.impl;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.repository.specification.SpecificationManager;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final SpecificationManager<Product> specificationManager;

    @Autowired
    public ProductServiceImpl(ProductRepository repository,
                              SpecificationManager<Product> specificationManager) {
        this.repository = repository;
        this.specificationManager = specificationManager;
    }

    @Override
    public Product save(Product entity) {
        return repository.save(entity);
    }

    @Override
    public Product get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No product with " + id + "."));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findAll(Map<String, String> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Product> sp = specificationManager.get(entry.getKey(),
                    entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(sp) : specification.and(sp);
        }
        return repository.findAll(specification);
    }
}
