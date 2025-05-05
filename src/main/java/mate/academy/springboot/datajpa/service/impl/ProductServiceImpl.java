package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dao.ProductRepository;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    public Product add(Product product) {
        return repository.save(product);
    }

    @Override
    public Product get(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Product update(Product product) {
        return repository.saveAndFlush(product);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findAllByPricing(BigDecimal from, BigDecimal to) {
        return repository.findProductsByPriceBetween(from, to);
    }

    @Override
    public List<Product> findAllByCategories(List<String> categories) {
        return repository.findProductsByCategoryNameIn(categories);
    }
}
