package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category createOrUpdate(Category category) {
        return repository.save(category);
    }

    @Override
    public Category getById(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
