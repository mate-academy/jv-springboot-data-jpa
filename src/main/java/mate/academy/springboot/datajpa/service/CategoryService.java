package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    void deleteById(Long id);

    Category findById(Long id);

    Category update(Category toModel);

    void create(Category toModel);
}
