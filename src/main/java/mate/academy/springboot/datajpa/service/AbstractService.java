package mate.academy.springboot.datajpa.service;

public interface AbstractService<T> {
    T create(T model);

    T get(Long id);

    void delete(Long id);

    T update(T model);
}
