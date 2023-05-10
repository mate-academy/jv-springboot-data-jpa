package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Service;

public interface CategoryService {
    Category save(Category category);
    Category update(Category category);
    Category delete(Category category);
    List<Category> findAll();

}
