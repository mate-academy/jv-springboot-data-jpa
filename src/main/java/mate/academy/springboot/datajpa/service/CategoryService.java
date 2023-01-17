package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    void deleteById(Long id);

    Category getById(Long id);

    List<Category> findAll();
}
