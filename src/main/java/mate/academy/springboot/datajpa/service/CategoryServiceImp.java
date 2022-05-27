package mate.academy.springboot.datajpa.service;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp extends BaseService<Category> implements CategoryService {
    private final CategoryRepository repository;

    @Override
    @Transactional
    public Category create(Category product) {
        return repository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Category getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(
            String.format("Category with id %d is not found", id)));
    }

    @Override
    @Transactional
    public Category update(Long id, Category source) {
        source.setId(id);
        return repository.save(source);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Category findByName(String name) throws EntityNotFoundException {
        Category category = repository.findCategoryByName(name);
        if (category == null) {
            throw new EntityNotFoundException(
                String.format("Category with name %s is not found", name));
        }
        return category;
    }
}
