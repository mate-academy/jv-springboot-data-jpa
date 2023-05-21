package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.respons.ProductResponseDto;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
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
    private final ResponseDtoMapper<ProductResponseDto, Product> responseDtoMapper;
    private final RequestDtoMapper<ProductRequestDto, Product> requestDtoMapper;

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.save(requestDtoMapper.mapToModel(productRequestDto));
        return responseDtoMapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = requestDtoMapper.mapToModel(productRequestDto);
        product.setId(id);
        return responseDtoMapper.mapToDto(productService.update(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findAllByPrice(@RequestParam BigDecimal from,
                                                  @RequestParam BigDecimal to) {
        List<Product> allPriceBetween = productService.findAllPriceBetween(from, to);
        return allPriceBetween.stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> findAllByCategories(@RequestParam List<String> categories) {
        return productService.findAllByCategory(categories).stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
