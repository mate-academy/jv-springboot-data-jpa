package mate.academy.springboot.datajpa.service;

import java.util.Optional;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> get(Long categoryId) {
        return Optional.of(categoryRepository.getById(categoryId));
    }

    @Override
    public void deleteById(Long categoryId) {
        categoryRepository.getById(categoryId);
    }

    @Override
    public void update(Category category) {
        categoryRepository.save(category);
    }
}
