package mate.academy.springboot.datajpa.service.impl;

import mate.academy.springboot.datajpa.exception.DataProcessingException;
import mate.academy.springboot.datajpa.repository.GeneralRepository;
import mate.academy.springboot.datajpa.service.GeneralService;

public class GeneralServiceImpl<T, I> implements GeneralService<T, I> {
    protected final GeneralRepository<T, I> repository;

    public GeneralServiceImpl(GeneralRepository<T, I> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T get(I id) {
        return repository.findById(id).
                orElseThrow(() -> new DataProcessingException("No entity with " + id + "."));
    }

    @Override
    public void delete(I id) {
        repository.deleteById(id);
    }
}
