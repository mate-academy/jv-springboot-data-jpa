package mate.academy.springboot.datajpa.service;

public interface AbstractService<T> {
    T save(T entity);

    T get(Long id);

    void delete(Long id);
}
