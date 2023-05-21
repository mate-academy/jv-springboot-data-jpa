package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    Category getById(Long id);

    void delete(Long id);

    Category update(Category category);

    List<Category> findAll();
}
