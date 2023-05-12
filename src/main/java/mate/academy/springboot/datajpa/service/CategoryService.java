package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    Category save(Category category);

    List<Category> findAll();

    void delete(Long id);

    Category get(Long id);
}
