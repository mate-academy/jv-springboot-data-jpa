package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final CategoryService service;

    public ProductServiceImpl(ProductRepository repository,
                              CategoryService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product get(Long id) {
        return repository.getById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Product update(Product product, Long id) {
        if (!repository.getById(id).equals(null)) {
            product.setId(id);
            return repository.save(product);
        }
        throw new RuntimeException("Product by id " + id + " doesn't exist");
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return repository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> findAllByCategories(List<Long> categoriesIds) {
        return repository.findAllByCategories(categoriesIds);
    }
}
