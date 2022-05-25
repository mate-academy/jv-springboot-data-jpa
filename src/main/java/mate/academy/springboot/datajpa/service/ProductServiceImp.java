package mate.academy.springboot.datajpa.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.mapper.ProductMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImp extends BaseService<Product> implements ProductService {

    private final ProductMapper mapper;
    private final ProductRepository repository;

    @Override
    @Transactional
    public Product create(Product entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Product update(Long id, Product source) {
        return repository.findById(id).map(target -> update(source, target)).orElse(null);
    }

    private Product update(Product source, Product target) {
        mapper.mapUpdate(source, target);
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
