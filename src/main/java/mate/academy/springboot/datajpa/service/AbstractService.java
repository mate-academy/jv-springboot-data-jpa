package mate.academy.springboot.datajpa.service;

public interface AbstractService<T> {
    T save(T t);

    T get(Long id);

    void delete(Long id);

    T update(T t);
}
