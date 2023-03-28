package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category product);

    Category gteCategoryById(Long id);

    void deleteCategoryById(Long id);

    Category updateCategory(Category category);
}
