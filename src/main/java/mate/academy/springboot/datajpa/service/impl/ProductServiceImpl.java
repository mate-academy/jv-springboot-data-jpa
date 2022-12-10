package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return repository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> getAllByCategoryId(List<Long> categoriesId) {
        return repository.findAllByCategoryId(categoriesId);
    }
}
