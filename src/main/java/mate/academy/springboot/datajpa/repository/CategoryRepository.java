package mate.academy.springboot.datajpa.repository;

import java.util.Optional;
import mate.academy.springboot.datajpa.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
