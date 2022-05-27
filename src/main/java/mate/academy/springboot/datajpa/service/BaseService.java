package mate.academy.springboot.datajpa.service;

public abstract class BaseService<T> {
    abstract T create(T entity);

    abstract T getById(Long id);

    abstract T update(T entity);

    abstract void delete(Long id);
}
