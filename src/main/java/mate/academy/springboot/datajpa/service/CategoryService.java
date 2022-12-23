package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    List<Category> findAll();

    void delete(Long id);

    Category get(Long id);
}
