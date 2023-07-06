package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Service;

public interface CategoryService {
    public Category save(Category category);

    public List<Category> findAll();

    public Category getById(Long id);

    public Category update(Category category);

    public void delete(Category category);

    public void deleteById(Long id);
}
