package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category saveCategory(Category category);

    Category getCategoryById(Long id);

    void deleteCategoryById(Long id);

    Category updateCategory(Category category);
}
