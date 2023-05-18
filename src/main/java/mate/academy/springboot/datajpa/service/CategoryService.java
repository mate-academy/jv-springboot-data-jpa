package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category create(Category category);

    Category get(Long id);

    boolean delete(Category category);

    Category update(Category category);
}
