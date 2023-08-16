package mate.academy.springboot.datajpa.service;

public interface GenersalService<T> {
    T create(T model);

    T get(Long id);

    void remove(Long id);
}
