package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category create(Category category);

    Category getById(Long id);

    Category getByName(String name);

    Category deleteById(Long id);

    Category update(Long id, Category category);
}
