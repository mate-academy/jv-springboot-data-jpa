package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category get(Long id);

    Category add(Category category);

    void delete(Long id);
}
