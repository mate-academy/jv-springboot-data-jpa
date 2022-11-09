package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    void deleteCategoryById(Long id);

    Category findCategoryById(Long id);

    Category updateCategory(Category toModel);

    void addCategory(Category toModel);
}
