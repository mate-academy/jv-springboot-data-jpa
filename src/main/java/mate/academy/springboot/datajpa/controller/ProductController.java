package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.component.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.component.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
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
public class ProductController {
    private final ProductService productService;
    private final RequestDtoMapper<ProductRequestDto, Product> productRequestDtoMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> productResponseDtoMapper;

    public ProductController(ProductService productService,
                    RequestDtoMapper<ProductRequestDto, Product> productRequestDtoMapper,
                    ResponseDtoMapper<ProductResponseDto, Product> productResponseDtoMapper) {
        this.productService = productService;
        this.productRequestDtoMapper = productRequestDtoMapper;
        this.productResponseDtoMapper = productResponseDtoMapper;
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productResponseDtoMapper.mapToDto(productService.get(id));
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto dto) {
        Product product = productRequestDtoMapper.mapToModel(dto);
        return productResponseDtoMapper.mapToDto(productService.add(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id, @RequestBody ProductRequestDto dto) {
        Product product = productRequestDtoMapper.mapToModel(dto);
        product.setId(id);
        return productResponseDtoMapper.mapToDto(productService.update(product));
    }

    @GetMapping
    public List<ProductResponseDto> getAll(@RequestParam Map<String, String> params) {
        return productService.getAll(params).stream()
                .map(productResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
