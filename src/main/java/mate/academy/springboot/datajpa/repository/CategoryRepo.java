package mate.academy.springboot.datajpa.repository;

import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo {
    Category save(Category category);
}
