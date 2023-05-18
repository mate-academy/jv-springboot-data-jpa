package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.ProductMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper mapper;

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto dto) {
        return mapper.mapToDto(productService.save(mapper.mapToModel(dto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return mapper.mapToDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id, @RequestBody ProductRequestDto dto) {
        Product product = mapper.mapToModel(dto);
        product.setId(id);
        return mapper.mapToDto(productService.update(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findProductsByPriceBetween(@RequestParam BigDecimal from,
                                                               @RequestParam BigDecimal to) {
        return productService.findProductsByPriceBetween(from, to).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> findProductsByCategoryIn(
            @RequestParam("categories") List<String> categoryNames) {
        return productService.findProductsByCategoryNameIn(categoryNames).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }
}
