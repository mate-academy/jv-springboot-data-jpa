package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    void deleteById(Long id);

    Category getById(Long id);

    Category updateById(Long id, Category category);
}
