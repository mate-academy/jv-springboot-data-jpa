package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
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
        if (repository.existsById(product.getId())) {
            return repository.save(product);
        }
        throw new RuntimeException("Can't find product by id " + product.getId());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return repository.findProductsByPriceBetween(from, to);
    }

    @Override
    public List<Product> findAllByCategoryIn(List<String> categories) {
        return repository.findProductsByCategoryNameIn(categories);
    }
}
