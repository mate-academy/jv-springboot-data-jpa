package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    Category update(Long id, Category category);

    void delete(Long id);

    List<Category> findAll();

    Category findById(Long id);
}
