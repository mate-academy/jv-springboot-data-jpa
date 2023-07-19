package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractCrudService<T, I> implements CrudService<T, I> {
    private final JpaRepository<T, I> repository;
    private final Class<T> clazz;

    protected AbstractCrudService(JpaRepository<T, I> repository, Class<T> clazz) {
        this.repository = repository;
        this.clazz = clazz;
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T findById(I id) {
        return repository.findById(id).orElseThrow(
                () -> EntityNotFoundException.withId(clazz, id)
        );
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T update(T entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }
}
