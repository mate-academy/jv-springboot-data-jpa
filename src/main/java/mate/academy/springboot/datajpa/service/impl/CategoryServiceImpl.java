package mate.academy.springboot.datajpa.service.impl;

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
    public Category get(Long id) {
        return categoryRepository.getReferenceById(id);
    }

    @Override
    public boolean delete(Long id) {
        return categoryRepository.deleteProductByIdIs(id);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }
}
