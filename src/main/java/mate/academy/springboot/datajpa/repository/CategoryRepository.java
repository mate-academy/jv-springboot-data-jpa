package mate.academy.springboot.datajpa.repository;

import javax.transaction.Transactional;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Transactional
    @Modifying
    @Query("update Category set name =?1 where id = ?2")
    void update(String name, Long id);
}
