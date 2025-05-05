package mate.academy.springboot.datajpa.service;

public interface GeneralService<T> {
    T add(T model);

    T get(Long id);

    T update(T model);

    void delete(Long id);
}
