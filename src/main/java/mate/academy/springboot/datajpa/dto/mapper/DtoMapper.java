package mate.academy.springboot.datajpa.dto.mapper;

public interface DtoMapper<T, Q, S> {
    public S toDto(T model);

    public T toModel(Q requestDto);
}
