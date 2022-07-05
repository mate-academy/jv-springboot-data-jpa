package mate.academy.springboot.datajpa.service.impl;

import java.util.NoSuchElementException;
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
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category get(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String
                        .format("Element 'Category' with id=%d not exist.", id)));
    }

    @Override
    public void delete(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }
}
