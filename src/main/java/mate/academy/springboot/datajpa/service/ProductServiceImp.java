package mate.academy.springboot.datajpa.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImp extends BaseService<Product> implements ProductService {
    private final ProductRepository repository;

    @Override
    @Transactional
    public Product create(Product entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(
            String.format("Product with id %s is not found", id)));
    }

    @Override
    @Transactional
    public Product update(Product source) {
        return repository.save(source);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Product> getByPriceBetween(Integer minPrice, Integer maxPrice) {
        return repository.getByPriceBetween(minPrice, maxPrice);
    }

    @Override
    @Transactional
    public List<Product> getByCategories(List<Category> categories) {
        return repository.getByCategories(categories);
    }
}
