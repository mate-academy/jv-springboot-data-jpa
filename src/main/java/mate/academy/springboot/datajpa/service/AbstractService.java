package mate.academy.springboot.datajpa.service;

import java.util.List;

public interface AbstractService<T> {

    T save(T element);

    T getById(Long id);

    void deleteById(Long id);

    T update(T element);

    List<T> getAll();
}
