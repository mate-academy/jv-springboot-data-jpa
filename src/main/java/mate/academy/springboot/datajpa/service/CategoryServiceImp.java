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
        return repository.getById(id);
    }

    @Override
    @Transactional
    public Category update(Long id, Category source) {
        Category target = repository.getById(id);
        return update(source, target);
    }

    private Category update(Category source, Category target) {
        if (target == null) {
            return null;
        }
        if (source.getId() != null) {
            target.setId(source.getId());
        }
        if (source.getDeleted() != null) {
            target.setDeleted(source.getDeleted());
        }
        if (source.getName() != null) {
            target.setName(source.getName());
        }
        return repository.save(target);
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
