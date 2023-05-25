package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.repository.specification.SpecificationManager;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SpecificationManager<Product> specificationManager;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get Product from DB by ID: " + id));
    }

    @Override
    public boolean delete(Long id) {
        return productRepository.deleteProductByIdIs(id);
    }

    @Override
    public Product update(Product product) {
        try {
            get(product.getId());
            return productRepository.save(product);
        } catch (RuntimeException e) {
            throw new RuntimeException("Can't update Product in DB: " + product);
        }
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal priceOne, BigDecimal priceTwo) {
        return productRepository.findAllByPriceBetween(priceOne, priceTwo);
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
        return productRepository.findAll(specification);
    }
}
