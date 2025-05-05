package mate.academy.springboot.service.mapper;

public interface UpdateRequestDtoMapper<T, D> {
    T toModelUpdate(D d);
}
