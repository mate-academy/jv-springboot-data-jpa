package mate.academy.springboot.datajpa.dao;

import mate.academy.springboot.datajpa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
