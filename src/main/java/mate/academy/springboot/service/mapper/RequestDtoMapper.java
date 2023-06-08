package mate.academy.springboot.service.mapper;

public interface RequestDtoMapper<T, D> {
    T toModel(D t);
}
