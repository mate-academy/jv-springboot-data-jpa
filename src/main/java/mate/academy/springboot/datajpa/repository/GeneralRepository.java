package mate.academy.springboot.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GeneralRepository<T, I> extends JpaRepository<T, I>, JpaSpecificationExecutor<T> {
}
