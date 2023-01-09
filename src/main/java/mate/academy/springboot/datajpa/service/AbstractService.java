package mate.academy.springboot.datajpa.service;

public interface AbstractService<T, K> {
    T create(T o);

    T get(K id);

    void delete(K id);

    T update(T o);
}
