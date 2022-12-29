package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    Category get(Long categoryId);

    void deleteById(Long categoryId);

    void update(Category category);
}
