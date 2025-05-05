package mate.academy.springboot.datajpa.service;

import java.util.List;

public interface CrudService<T, I> {
    T save(T entity);

    T findById(I id);

    List<T> findAll();

    T update(T entity);

    void delete(T entity);
}
