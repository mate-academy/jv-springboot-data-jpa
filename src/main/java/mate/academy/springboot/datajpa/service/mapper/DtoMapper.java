package mate.academy.springboot.datajpa.service.mapper;

import org.springframework.stereotype.Component;

@Component
public interface DtoMapper<T, D, R> {
    D toDto(T entity);

    T toModel(R dto);
}
