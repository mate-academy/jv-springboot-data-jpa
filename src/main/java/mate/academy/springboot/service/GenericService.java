package mate.academy.springboot.service;

public interface GenericService<T> {
    T save(T entity);

    T getById(Long id);

    T update(T entity);

    void delete(Long id);
}
