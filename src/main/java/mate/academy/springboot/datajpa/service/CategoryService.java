package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    List<Category> findAll();

    Category getById(Long id);

    void delete(Category category);

    Category update(Category category);
}
