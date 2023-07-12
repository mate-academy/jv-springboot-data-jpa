package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category add(Category category);

    Category findById(Long categoryId);

    void deleteById(Long categoryId);

    Category update(Category category);
}
