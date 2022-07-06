package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    Category findById(Long id);

    Category findByName(String name);

    void deleteById(Long id);

    Category updateById(Long id, Category category);
}
