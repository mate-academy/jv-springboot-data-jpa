package controller;

import dto.request.ProductRequestDto;
import dto.response.ProductResponseDto;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import model.Product;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ProductService;
import service.mapper.RequestDtoMapper;
import service.mapper.ResponseDtoMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final RequestDtoMapper<ProductRequestDto, Product> requestDtoMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> responseDtoMapper;

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = requestDtoMapper.toModel(productRequestDto);
        return responseDtoMapper.toDto(productService.save(product));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return responseDtoMapper.toDto(productService.getById(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = requestDtoMapper.toModel(productRequestDto);
        product.setId(id);
        return responseDtoMapper.toDto(productService.save(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getAllWherePriceBetween(@RequestParam BigDecimal from,
                                                            @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(responseDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> getAllCategoryIn(@RequestParam Map<String, String> params) {
        return productService.findAll(params).stream()
                .map(responseDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
