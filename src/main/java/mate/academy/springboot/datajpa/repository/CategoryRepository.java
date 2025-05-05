package mate.academy.springboot.datajpa.repository;

import mate.academy.springboot.datajpa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Category c SET c.name = :#{#category.name} WHERE c.id = :#{#category.id}")
    int update(Category category);
}
