package mate.academy.springboot.datajpa.service;

public interface CrudService<T, L> {
    T save(T t);

    T get(L id);

    void delete(L id);

    T update(T t);
}
