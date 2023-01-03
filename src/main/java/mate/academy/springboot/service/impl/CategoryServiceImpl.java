package mate.academy.springboot.service.impl;

import mate.academy.springboot.model.Category;
import mate.academy.springboot.repository.CategoryRepository;
import mate.academy.springboot.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.getReferenceById(id);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void update(Category category) {
        categoryRepository.save(category);
    }
}