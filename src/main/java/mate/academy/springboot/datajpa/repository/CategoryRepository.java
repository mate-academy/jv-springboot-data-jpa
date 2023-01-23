package mate.academy.springboot.datajpa.repository;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("FROM Category WHERE name IN (:categoryNames)")
    List<Category> findByNameIn(List<String> categoryNames);
}
