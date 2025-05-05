package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.specification.ProductSpecificationManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ProductSpecificationManager productSpecificationManager;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Product with id " + id + " not found")
        );
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.findByPriceBetween(from, to);
    }

    @Override
    public List<Product> getAll(Map<String, String> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (specification == null) {
                specification = Specification.where(
                        productSpecificationManager.getSpecification(
                                entry.getKey(), entry.getValue().split(",")
                        )
                );
            } else {
                specification = specification.and(
                        productSpecificationManager.getSpecification(
                                entry.getKey(), entry.getValue().split(",")
                        )
                );
            }
        }
        return productRepository.findAll(specification);
    }
}
