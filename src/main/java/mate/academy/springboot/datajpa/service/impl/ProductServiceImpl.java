package mate.academy.springboot.datajpa.service.impl;

import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.component.spesification.SpecificationManager;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private SpecificationManager<Product> specificationManager;

    public ProductServiceImpl(ProductRepository productRepository,
                              SpecificationManager<Product> specificationManager) {
        this.productRepository = productRepository;
        this.specificationManager = specificationManager;
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAll(Map<String, String> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Product> sp = specificationManager
                    .get(entry.getKey(), entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(sp) : specification.and(sp);
        }
        return specification == null
                ? productRepository.findAll()
                : productRepository.findAll(specification);
    }
}
