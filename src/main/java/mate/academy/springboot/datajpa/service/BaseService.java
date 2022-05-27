package mate.academy.springboot.datajpa.service;

public abstract class BaseService<T> {

    abstract T create(T product);

    abstract T getById(Long id);

    abstract T update(Long id, T product);

    abstract void delete(Long id);
}
