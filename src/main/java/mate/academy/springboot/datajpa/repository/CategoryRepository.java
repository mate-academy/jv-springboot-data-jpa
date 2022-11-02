package mate.academy.springboot.datajpa.repository;

import javax.transaction.Transactional;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Transactional
    @Modifying
    @Query(value = "update Category c set c.name = ?2 where c.id = ?1")
    void update(Long id, String name);
}
