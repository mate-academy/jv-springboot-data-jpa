package mate.academy.springboot.datajpa.repository;

import mate.academy.springboot.datajpa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Modifying
    @Query(value = "update Category c set c.name = :name where c.id = :id")
    void updateCategory(@Param(value = "id") Long id, @Param(value = "name") String name);
}
