package mate.academy.springboot.datajpa.service;

public interface GenericService<T> {
    T create(T t);

    T getById(Long id);

    void deleteById(Long id);

    T update(T t);
}
