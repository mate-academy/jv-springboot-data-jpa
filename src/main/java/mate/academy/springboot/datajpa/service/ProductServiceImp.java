package mate.academy.springboot.datajpa.service;

import java.util.List;
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
        return repository.getById(id);
    }

    @Override
    @Transactional
    public Product update(Long id, Product source) {
        Product target = repository.getById(id);
        return update(source, target);
    }

    private Product update(Product source, Product target) {
        if (target == null) {
            return null;
        }
        if (source.getId() != null) {
            target.setId(source.getId());
        }
        if (source.getDeleted() != null) {
            target.setDeleted(source.getDeleted());
        }
        if (source.getTitle() != null) {
            target.setTitle(source.getTitle());
        }
        if (source.getPrice() != null) {
            target.setPrice(source.getPrice());
        }
        if (source.getCategory() != null) {
            target.setCategory(source.getCategory());
        }
        return repository.save(target);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Product> getByPriceBetween(Integer lowerPrice, Integer higherPrice) {
        return repository.getByPriceBetween(lowerPrice, higherPrice);
    }

    @Override
    @Transactional
    public List<Product> getByCategories(List<Category> categories) {
        return repository.getByCategories(categories);
    }
}
