package mate.academy.springboot.datajpa.repositories;

import mate.academy.springboot.datajpa.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
