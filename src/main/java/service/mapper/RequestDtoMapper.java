package service.mapper;

public interface RequestDtoMapper<D, T> {
    T toModel(D dto);
}
