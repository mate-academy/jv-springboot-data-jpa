package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final DtoMapper<ProductRequestDto, ProductResponseDto, Product> mapper;
    private final ProductService productService;

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto dto) {
        Product product = productService.save(mapper.mapToModel(dto));
        return mapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        Product product = productService.get(id);
        return mapper.mapToDto(product);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        productService.remove(id);
        return "Product was deleted :)";
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id, @RequestBody ProductRequestDto dto) {
        Product product = mapper.mapToModel(dto);
        product.setId(id);
        return mapper.mapToDto(productService.save(product));
    }

    @GetMapping("/between-prices")
    public List<ProductResponseDto> getAllByPrice(@RequestParam BigDecimal from,
                                       @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/in-categories")
    public List<ProductResponseDto> getAllProductsInCategories(@RequestParam String[] categories) {
        return productService.findAllByCategoryNameIn(List.of(categories)).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }
}
