package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category create(Category category);

    Category get(Long id);

    Category update(Category category);

    void delete(Long id);

    Category findByName(String name);
}
