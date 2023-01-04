package mate.academy.springboot.datajpa.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.persistance.CategoryRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
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
    public Category getById(Long id) {
        return getOrThrowException(id);
    }

    private Category getOrThrowException(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Category not found by id: " + id
                )
        );
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getByNames(List<String> name) {
        return categoryRepository.findAllByNameIn(name);
    }

    @Override
    public void delete(Long id) {
        Category category = getOrThrowException(id);
        categoryRepository.delete(category);
    }
}
