package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    Category getById(Long categoryId);

    void delete(Category category);

    Category update(Category category);

    Category getByName(String name);
}
