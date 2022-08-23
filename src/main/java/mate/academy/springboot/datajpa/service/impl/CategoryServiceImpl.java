package mate.academy.springboot.datajpa.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category get(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category
                .orElseThrow(() -> new NoSuchElementException("Category with id "
                        + id + " not found"));

    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Category category) {
        Category retrievedCategory = categoryRepository.getById(category.getId());
        retrievedCategory.setName(category.getName());
        return categoryRepository.save(retrievedCategory);
    }
}
