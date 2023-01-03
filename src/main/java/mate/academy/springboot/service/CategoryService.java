package mate.academy.springboot.service;

import mate.academy.springboot.model.Category;

public interface CategoryService {
    Category add(Category category);

    Category getById(Long id);

    void delete(Long id);

    void update(Category category);
}
