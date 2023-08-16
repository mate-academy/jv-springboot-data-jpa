package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<ResponseT, EntityT, RequestT> {
    ResponseT toDto(EntityT entity);

    EntityT toEntity(RequestT request);
}
