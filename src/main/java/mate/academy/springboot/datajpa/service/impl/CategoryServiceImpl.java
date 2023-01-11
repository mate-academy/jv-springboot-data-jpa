package mate.academy.springboot.datajpa.service.impl;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find category by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
