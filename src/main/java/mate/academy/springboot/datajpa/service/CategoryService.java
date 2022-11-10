package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    Category get(Long id);

    Category update(Category category);

    boolean remove(Long id);

    List<Category> findAll();
}
