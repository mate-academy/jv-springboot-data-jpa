package mate.academy.springboot.datajpa.service.impl;

import java.util.Optional;
import mate.academy.springboot.datajpa.dao.CategoryRepository;
import mate.academy.springboot.datajpa.exception.ServiceDataException;
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
    public Category findById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new ServiceDataException("Can't find a category by Id : " + id + " !");
        }
        return categoryOptional.get();
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }
}
