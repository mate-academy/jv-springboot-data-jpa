package mate.academy.springboot.datajpa.controller;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.DtoMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final DtoMapper<ProductRequestDto, ProductResponseDto, Product> mapper;

    @PostMapping
    public ProductResponseDto create(@RequestBody @Valid ProductRequestDto dto) {
        Product mappedProduct = mapper.mapToModel(dto);
        Product product = service.add(mappedProduct);
        return mapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getByID(@PathVariable Long id) {
        Product product = service.getById(id);
        return mapper.mapToDto(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
            @RequestBody @Valid ProductRequestDto dto) {
        return mapper.mapToDto(service.update(mapper.mapToModel(dto), id));
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getByPrice(@RequestParam BigDecimal from,
            @RequestParam BigDecimal to) {
        return service.findAllByPriceBetween(from, to).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/categories")
    public List<ProductResponseDto> getByCategorie(@RequestParam List<String> categoryNames) {
        return service.findAllByCategoryNameIn(categoryNames).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }
}
