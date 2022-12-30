package mate.academy.springboot.datajpa.service;

public interface AbstractService<T, P> {
    T save(T o);

    T get(P id);

    void delete(P id);

    T update(T o);
}
