package mate.academy.springboot.datajpa.service;

public interface GeneralService<T, I> {
    T save(T entity);

    T get(I id);

    void delete(I id);
}
