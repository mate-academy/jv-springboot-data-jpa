package mate.academy.springboot.datajpa.service;

import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can't get category with id " + id));
    }

    @Override
    public Category update(Long id, Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        Category categoryFromDb = categoryRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can't get category with id " + id));
        categoryFromDb.setName(category.getName());
        return categoryRepository.save(categoryFromDb);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
