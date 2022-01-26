package mate.academy.springboot.datajpa.service;

import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    Category getById(Long id);

    Category update(Category category);

    void delete(Long id);

    List<Category> findAll(Map<String, String> params);
}
