package mate.academy.springboot.datajpa.service.mapper;

/**
 *
 * @param <R> RequestDto
 * @param <S> ResponseDto
 * @param <M> Model
 */
public interface DtoMapper<R, S, M> {
    M mapToModel(R dto);

    S mapToDto(M model);
}
