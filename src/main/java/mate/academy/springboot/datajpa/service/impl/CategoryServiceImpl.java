package mate.academy.springboot.datajpa.service.impl;

import mate.academy.springboot.datajpa.exception.DataProcessingException;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public Category get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataProcessingException("No category with " + id + "."));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
