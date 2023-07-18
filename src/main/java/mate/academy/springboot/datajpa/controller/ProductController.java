package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@NoArgsConstructor
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
    private RequestDtoMapper<ProductRequestDto, Product> requestMapper;
    private ResponseDtoMapper<Product, ProductResponseDto> responseMapper;

    @PostMapping
    public ProductResponseDto create(@RequestBody @Valid ProductRequestDto productRequestDto) {
        Product product = requestMapper.mapToModel(productRequestDto);
        Category category = categoryService.get(productRequestDto.getCategoryId());
        product.setCategory(category);
        return responseMapper.mapToDto(productService.save(product));
    }

    @GetMapping
    public ProductResponseDto get(@RequestParam @Positive Long id) {
        return responseMapper.mapToDto(productService.get(id));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getAllByPrice(@RequestParam @Positive BigDecimal from,
                                                  @RequestParam @Positive BigDecimal to) {
        return productService.getAllByPriceBetween(from, to).stream()
                .map(responseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> getAllByCategories(@RequestParam Set<String> categories) {
        return productService.getAllByCategoriesIn(categories).stream()
                .map(responseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping
    public ProductResponseDto update(@RequestParam @Positive Long id,
                                     @RequestBody @Valid ProductRequestDto productRequestDto) {
        Product product = requestMapper.mapToModel(productRequestDto);
        product.setId(id);
        Category category = categoryService.get(productRequestDto.getCategoryId());
        product.setCategory(category);
        return responseMapper.mapToDto(productService.save(product));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam @Positive Long id) {
        productService.remove(id);
    }
}
