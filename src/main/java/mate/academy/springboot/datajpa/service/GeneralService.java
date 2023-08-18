package mate.academy.springboot.datajpa.service;

public interface GeneralService<T> {
    T save(T model);

    T get(Long id);

    void remove(Long id);
}
