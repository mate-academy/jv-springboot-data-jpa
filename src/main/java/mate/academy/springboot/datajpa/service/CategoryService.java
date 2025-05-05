package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Component;

@Component
public interface CategoryService {
    Category save(Category category);

    List<Category> findAll();

    Category findById(Long id);

    void delete(Long id);

    Category update(Category category);
}
