package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category add(Category category);

    void update(Category category);

    Category get(Long id);

    void remove(Long id);
}
