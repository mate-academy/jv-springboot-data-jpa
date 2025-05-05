package mate.academy.springboot.datajpa.service.impl;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public Category add(Category category) {
        return repository.save(category);
    }

    @Override
    public Category get(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Category update(Category category) {
        if (repository.existsById(category.getId())) {
            return repository.save(category);
        }
        throw new RuntimeException("Can't find category by id " + category.getId());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
