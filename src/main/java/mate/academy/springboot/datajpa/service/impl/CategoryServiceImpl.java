package mate.academy.springboot.datajpa.service.impl;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        return categoryRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't get a category by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        if (optionalCategory.isPresent()) {
            Category categoryFromDb = optionalCategory.get();
            categoryFromDb.setName(category.getName());
            return categoryRepository.save(categoryFromDb);
        } else {
            throw new NoSuchElementException("There is no such category " + category);
        }
    }
}
