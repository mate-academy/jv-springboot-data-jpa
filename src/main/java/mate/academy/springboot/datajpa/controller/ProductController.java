package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.model.dto.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.dto.request.product.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.product.ProductResponseDto;
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
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final RequestDtoMapper<ProductRequestDto, Product> productRequestDtoMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> productResponseDtoMapper;

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        return mapToDto(productService.save(mapToModel(requestDto, null)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return mapToDto(productService.findById(id));
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                         @RequestParam BigDecimal to) {
        return mapToDtos(productService.findAllByPriceBetween(from, to));
    }

    @GetMapping
    public List<ProductResponseDto> getAll(@RequestParam Map<String, String> params) {
        return mapToDtos(productService.findAll(params));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        return mapToDto(productService.save(mapToModel(requestDto, id)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    private Product mapToModel(ProductRequestDto dto, Long id) {
        return productRequestDtoMapper.mapToModel(dto, id);
    }

    private ProductResponseDto mapToDto(Product product) {
        return productResponseDtoMapper.mapToDto(product);
    }

    private List<ProductResponseDto> mapToDtos(List<Product> products) {
        return products.stream()
                .map(productResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
