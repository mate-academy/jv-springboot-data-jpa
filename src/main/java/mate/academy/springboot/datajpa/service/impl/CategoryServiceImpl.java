package mate.academy.springboot.datajpa.service.impl;

import mate.academy.springboot.datajpa.dao.CategoryRepository;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.getById(id);
    }
}
