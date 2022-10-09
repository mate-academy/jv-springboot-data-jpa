package mate.academy.springboot.datajpa.service;

public interface CrudService<T> {
    T save(T t);

    T get(Long id);

    void delete(Long id);
}
