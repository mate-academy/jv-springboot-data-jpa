package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category get(Long id);

    void delete(Long id);

    Category save(Category category);

    void update(Long id, Category category);
}
