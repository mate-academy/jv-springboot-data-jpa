package mate.academy.springboot.datajpa.service;

public interface GenericService<T> {
    T save(T t);

    T getById(Long id);

    void deleteById(Long id);
}
