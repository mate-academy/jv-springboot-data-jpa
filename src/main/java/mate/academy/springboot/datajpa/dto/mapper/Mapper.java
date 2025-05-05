package mate.academy.springboot.datajpa.dto.mapper;

public interface Mapper<M, R, F> {
    F mapToDto(M model);

    M mapToModel(R requestDto);
}
