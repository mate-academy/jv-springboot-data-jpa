package mate.academy.springboot.datajpa.service.impl;

import java.util.NoSuchElementException;
import mate.academy.springboot.datajpa.domain.Category;
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
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Can't find a category by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name).orElseThrow(() ->
                new NoSuchElementException("This category: " + name + " doesn't exist"));
    }
}
