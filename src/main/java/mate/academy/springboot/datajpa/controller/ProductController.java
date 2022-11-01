package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
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
public class ProductController {
    private final ProductService productService;
    private final ResponseDtoMapper<ProductResponseDto, Product> responseDtoMapper;
    private final RequestDtoMapper<ProductRequestDto, Product> requestDtoMapper;

    public ProductController(ProductService productService,
                             ResponseDtoMapper<ProductResponseDto,
                                     Product> responseDtoMapper,
                             RequestDtoMapper<ProductRequestDto,
                                     Product> requestDtoMapper) {
        this.productService = productService;
        this.responseDtoMapper = responseDtoMapper;
        this.requestDtoMapper = requestDtoMapper;
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody @Valid ProductRequestDto requestDto) {
        Product product = productService.save(requestDtoMapper.mapToModel(requestDto));
        return responseDtoMapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return responseDtoMapper.mapToDto(product);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid ProductRequestDto requestDto) {
        Product product = requestDtoMapper.mapToModel(requestDto);
        product.setId(id);
        return responseDtoMapper.mapToDto(productService.update(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/price")
    public List<ProductResponseDto> findAllByPriceBetween(@RequestParam BigDecimal from,
                                               @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
                .stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> findAllByCategory(@RequestParam List<String> categories) {
        return productService.findAllByCategory(categories)
                .stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
