package mate.academy.springboot.datajpa.service.impl;

import java.util.NoSuchElementException;
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
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find the category by "
                        + "id: " + id));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Category category, Long id) {
        Category oldCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find the category by "
                        + "id: " + id));
        oldCategory.setName(category.getName());
        oldCategory.setId(id);
        return categoryRepository.save(oldCategory);
    }
}
