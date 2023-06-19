package mate.academy.springboot.datajpa.service;

interface AbstractService<T> {
    T save(T category);

    T get(Long id);

    void delete(Long id);

    T update(T category);
}
