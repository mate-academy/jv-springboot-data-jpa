package mate.academy.springboot.datajpa.service.impl;

import mate.academy.springboot.datajpa.dao.CategoryRepository;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

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
        return repository.saveAndFlush(category);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
