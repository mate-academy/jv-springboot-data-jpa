package mate.academy.springboot.datajpa.service;

public interface Service<E, T> {
    E save(E e);

    E get(T id);

    void delete(T id);

    E update(E e);
}
