package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.mapper.ProductDtoMapper;
import mate.academy.springboot.datajpa.model.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;
    private final ProductDtoMapper mapper;

    @Autowired
    public ProductController(ProductService service, ProductDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto dto) {
        return mapper.toResponseDto(service.save(mapper.toModel(dto)));
    }

    @GetMapping
    public ProductResponseDto get(@RequestParam Long id) {
        return mapper.toResponseDto(service.get(id));
    }

    @DeleteMapping
    public String delete(@RequestParam Long id) {
        service.delete(id);
        return String.format("Product with id:%d has been deleted", id);
    }

    @GetMapping("/search")
    List<ProductResponseDto> findAll(@RequestParam Map<String, String> params) {
        return service.findAll(params).stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

}
