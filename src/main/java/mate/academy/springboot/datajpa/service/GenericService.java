package mate.academy.springboot.datajpa.service;

import java.util.List;

public interface GenericService<T> {
    T save(T entity);

    T findById(Long id);

    List<T> findAll();

    void update(T entity);

    void delete(Long id);
}
