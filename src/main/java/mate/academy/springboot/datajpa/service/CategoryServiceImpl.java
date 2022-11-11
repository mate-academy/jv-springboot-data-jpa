package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
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
        return categoryRepository.getById(id);
    }

    @Override
    public Category update(Category category) {
        Category categoryFromDB = categoryRepository.getById(category.getId());
        categoryFromDB.setTitle(category.getTitle());
        return categoryRepository.saveAndFlush(categoryFromDB);
    }

    @Override
    public boolean remove(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

}
