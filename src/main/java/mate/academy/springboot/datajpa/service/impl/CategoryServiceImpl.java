package mate.academy.springboot.datajpa.service.impl;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.exception.DbException;
import mate.academy.springboot.datajpa.model.entity.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new DbException(
                        "Category id #" + id + " absent in database"));
    }

    @Override
    public Category findByNameOrSave(String name) {
        return categoryRepository.findByName(name)
                .orElseGet(() -> categoryRepository.save(new Category(name)));
    }

    @Override
    public void delete(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new DbException(
                    "Can`t delete category id #" + id + " from database. No such category");
        }
    }

    @Override
    public Category update(Category category) {
        if (categoryRepository.findById(category.getId()).isPresent()) {
            return categoryRepository.save(category);
        }
        throw new DbException(
                "Can`t update category id #" + category.getId()
                        + " in database. No such category");
    }
}
