package mate.academy.springboot.datajpa.repository;

import mate.academy.springboot.datajpa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByIdIn(List<Long> categoriesId);
}
