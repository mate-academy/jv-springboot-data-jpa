package mate.academy.springboot.datajpa.dto.mapper;

public interface MapperDto<Q, S, M> {
    S toDto(M model);

    M toModel(Q dtoRequest);
}
