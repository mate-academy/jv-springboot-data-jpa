package mate.academy.springboot.datajpa.service;

import java.util.Optional;

public interface BaseService<T> {
    T save(T entity);

    Optional<T> get(Long id);

    void remove(Long id);

    T update(T entity);
}
