package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category add(Category category);

    void delete(Long id);

    Category update(Category category);

    Category getById(Long id);

    List<Category> getAll();
}
