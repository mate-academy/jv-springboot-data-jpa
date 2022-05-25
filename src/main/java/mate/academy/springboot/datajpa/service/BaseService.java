package mate.academy.springboot.datajpa.service;

import java.util.Optional;
import mate.academy.springboot.datajpa.model.BaseEntity;

public abstract class BaseService<T extends BaseEntity> {

    abstract T create(T product);

    abstract Optional<T> findById(Long id);

    abstract T update(Long id, T product);

    abstract void delete(Long id);
}
