package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    //TODO delete not needed
    Category save(Category category);

    Category getById(Long id);

    Category update(Category category);

    void delete(Long id);

    //   List<Category> getAll();
}
