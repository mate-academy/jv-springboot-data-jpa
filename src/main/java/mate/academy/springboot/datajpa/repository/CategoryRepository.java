package mate.academy.springboot.datajpa.repository;

import java.util.Optional;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);

    Category save(Category category);

    void deleteById(Long id);

    @Transactional
    @Modifying
    @Query("update Category c set c.name = :name where c.id = :id")
    void update(@Param("name")String name, @Param("id")Long id);
}
