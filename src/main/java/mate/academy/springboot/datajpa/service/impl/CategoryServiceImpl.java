package mate.academy.springboot.datajpa.service.impl;

import java.util.List;
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
    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        if (categoryRepository.findById(id).isEmpty()) {
            throw new RuntimeException(("Can't find category by id " + id
                    + " for delete"));
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Category category) {
        if (categoryRepository.findById(category.getId()).isPresent()) {
            return categoryRepository.saveAndFlush(category);
        }
        throw new RuntimeException(("Can't find category by id " + category.getId()
                + " for update"));
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find category by id " + id));
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
