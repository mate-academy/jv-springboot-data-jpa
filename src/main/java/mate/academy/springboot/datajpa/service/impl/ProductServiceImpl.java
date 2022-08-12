package mate.academy.springboot.datajpa.service.impl;

import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final SpecificationProvider specificationProvider;
    private final ProductRepository productRepository;

    public ProductServiceImpl(SpecificationProvider specificationProvider,
                              ProductRepository productRepository) {
        this.specificationProvider = specificationProvider;
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAll(Map<String, List<String>> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, List<String>> entry : params.entrySet()) {
            Specification<Product> sp = specificationProvider
                    .getSpecification(entry.getValue(), entry.getKey());
            specification = specification == null ? Specification.where(sp) : specification.and(sp);
        }
        return productRepository.findAll(specification);
    }

}
