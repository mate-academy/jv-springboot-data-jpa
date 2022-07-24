package mate.academy.springboot.datajpa.service.impl;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Category> findAllByIdIn(List<Long> categoriesId) {
        return categoryRepository.findAllByIdIn(categoriesId);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
