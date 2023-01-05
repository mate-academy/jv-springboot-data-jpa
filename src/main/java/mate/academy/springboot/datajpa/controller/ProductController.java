package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
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

    public ProductController(
            ProductService productService,
            RequestDtoMapper<ProductRequestDto, Product> productRequestDtoMapper,
            ResponseDtoMapper<ProductResponseDto, Product> productResponseDtoMapper) {
        this.productService = productService;
        this.productRequestDtoMapper = productRequestDtoMapper;
        this.productResponseDtoMapper = productResponseDtoMapper;
    }

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.save(
                productRequestDtoMapper.mapToModel(productRequestDto));
        return productResponseDtoMapper.mapToDto(product);
    }

    @GetMapping("{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productResponseDtoMapper.mapToDto(productService.getById(id));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = productRequestDtoMapper.mapToModel(productRequestDto);
        product.setId(id);
        return productResponseDtoMapper.mapToDto(productService.update(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findByPrice(@RequestParam Integer from,
                                                @RequestParam Integer to) {
        return productService.findAllByPriceBetween(from, to)
                .stream()
                .map(productResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<ProductResponseDto> findAllByCategories(@RequestParam Map<String, String> params) {
        return productService.findAllByCategories(params)
                .stream()
                .map(productResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
