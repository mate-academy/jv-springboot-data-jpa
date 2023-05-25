package mate.academy.springboot.datajpa.service.mapper;

/**
 *
 * @param <M> - model of entity
 * @param <D> - request dto of entity
 * @param <F> - response dto of entity
 */

public interface Mapper<M, D, F> {
    M toModel(D requestDto);

    F toDto(M model);
}
