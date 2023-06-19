package mate.academy.springboot.datajpa.service.mappers;

public interface DtoMapper<P, D, T> {
    P mapToModel(T t);

    D mapToDto(P dto);
}
