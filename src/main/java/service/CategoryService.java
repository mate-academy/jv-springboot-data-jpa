package service;

import model.Category;

public interface CategoryService {
    Category save(Category category);

    Category getById(Long id);

    void deleteById(Long id);
}
