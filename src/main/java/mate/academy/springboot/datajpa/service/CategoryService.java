package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category product);

    Category get(Long id);

    Category delete(Long id);

    Category update(Category category);

    List<Category> getAll();
}
