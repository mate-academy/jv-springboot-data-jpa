package mate.academy.springboot.datajpa.service;

import java.util.List;
import java.util.Optional;
import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category create(Category category);

    void update(Category category);

    List<Category> getAll();

    Optional<Category> getById(Long id);

    public void deleteById(Long id);
}
