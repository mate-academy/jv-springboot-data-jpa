package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category findById(Long id);

    Category create(Category category);

    void delete(Long id);

    Category update(Category category);
}
