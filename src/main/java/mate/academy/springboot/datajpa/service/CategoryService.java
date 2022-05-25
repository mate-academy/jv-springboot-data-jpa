package mate.academy.springboot.datajpa.service;

import java.util.Optional;
import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {

    Optional<Category> findByName(String name);
}
