package mate.academy.springboot.datajpa.repository;

import mate.academy.springboot.datajpa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.id = ?1 and c.deleted = false")
    Category getById(Long id);

    @Query("update Category c set c.deleted = true where c.id = ?1 and c.deleted = false")
    void deleteById(Long id);

    @Modifying
    @Query("select c from Category c where c.name = ?1 and c.deleted = false")
    Category findCategoryByName(String name);
}
