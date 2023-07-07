package mate.academy.springboot.datajpa.service.impl;

import java.util.NoSuchElementException;
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
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NoSuchElementException("Can't get category by id: " + id);
        } else {
            return categoryRepository.getById(id);
        }
    }

    @Override
    public Category update(Category category) {
        if (categoryRepository.existsById(category.getId())) {
            return categoryRepository.save(category);
        } else {
            throw new NoSuchElementException("Can't update category: " + category);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Can't delete category with id: " + id);
        }
    }

}
