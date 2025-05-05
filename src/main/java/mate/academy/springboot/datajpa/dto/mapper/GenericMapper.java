package mate.academy.springboot.datajpa.dto.mapper;

public interface GenericMapper<E, RequestDtoT, ResponseDtoT> {
    E mapToModel(RequestDtoT requestDto);

    ResponseDtoT mapToDto(E model);
}
