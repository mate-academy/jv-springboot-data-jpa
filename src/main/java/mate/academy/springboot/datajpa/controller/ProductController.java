package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
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
    private final DtoMapper<ProductResponseDto, ProductRequestDto, Product> dtoMapper;

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return dtoMapper
                .mapToDto(productService.getById(id));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                         @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(dtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> getAllByCategoryIn(
            @RequestParam("category") List<String> categories) {
        return productService.findByCategories(categories).stream()
                .map(dtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto requestDto) {
        return dtoMapper
                .mapToDto(productService.create(dtoMapper.mapToModel(requestDto)));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = dtoMapper.mapToModel(requestDto);
        product.setId(id);
        return dtoMapper.mapToDto(productService.update(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
