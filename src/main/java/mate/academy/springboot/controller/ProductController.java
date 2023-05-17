package mate.academy.springboot.controller;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.dto.mapper.RequestDtoMapper;
import mate.academy.springboot.dto.mapper.ResponseDtoMapper;
import mate.academy.springboot.dto.request.ProductRequestDto;
import mate.academy.springboot.dto.response.ProductResponseDto;
import mate.academy.springboot.model.Product;
import mate.academy.springboot.service.ProductService;
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
    private final ProductService productService;
    private final RequestDtoMapper<ProductRequestDto, Product> requestMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> responseMapper;

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.save(requestMapper.toModel(requestDto));
        return responseMapper.toDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto findById(@PathVariable Long id) {
        return responseMapper.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(
            @PathVariable Long id,
            @RequestBody ProductRequestDto requestDto) {
        Product product = requestMapper.toModel(requestDto);
        product.setId(id);
        return responseMapper.toDto(productService.save(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findAllByPriceBetween(
            @RequestParam BigDecimal from,
            @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(responseMapper::toDto)
                .toList();
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> findAllByCategoriesNameIn(
            @RequestParam List<String> categoryNames) {
        return productService.findAllByCategoryName(categoryNames).stream()
                .map(responseMapper::toDto)
                .toList();
    }
}
