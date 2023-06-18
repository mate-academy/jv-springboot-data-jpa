package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.models.Category;

public interface CategoryService {
    Category save(Category category);

    Category get(Long id);

    void delete(Long id);

    Category update(Category category);
}
