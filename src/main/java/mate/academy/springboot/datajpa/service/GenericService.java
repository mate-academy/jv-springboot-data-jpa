package mate.academy.springboot.datajpa.service;

public interface GenericService<E> {
    E add(E model);

    E get(Long id);

    E update(E model);

    void delete(Long id);
}
