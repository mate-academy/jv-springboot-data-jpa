package mate.academy.springboot.datajpa.service;

public interface BaseService<T> {
    T save(T entity);

    T get(Long id);

    void remove(Long id);
}
