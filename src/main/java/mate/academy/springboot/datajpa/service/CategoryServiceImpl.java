package mate.academy.springboot.datajpa.service;

import java.util.List;
import java.util.Optional;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryDao, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryDao;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void update(Category category) {
        categoryRepository.update(category.getId(), category.getName());
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
