package mate.academy.springboot.datajpa.service;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractService<E, T> implements Service<E, T> {
    private final JpaRepository<E, T> repository;

    public AbstractService(JpaRepository<E, T> repository) {
        this.repository = repository;
    }

    @Override
    public E save(E e) {
        return repository.save(e);
    }

    @Override
    public E get(T id) {
        return repository.getReferenceById(id);
    }

    @Override
    public void delete(T id) {
        repository.deleteById(id);
    }

    @Override
    public E update(E e) {
        return repository.save(e);
    }
}
