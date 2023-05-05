package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
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
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper mapper;

    @PostMapping()
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        Product save = productService.save(mapper.toProductModel(requestDto));
        return mapper.toResponseDto(save);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        Product byId = productService.getById(id);
        return mapper.toResponseDto(byId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        return mapper.toResponseDto(productService.update(
                mapper.toProductModel(productRequestDto)));
    }

    @GetMapping("/findAllByPrice")
    public List<ProductResponseDto> findAllByPriceBetween(@RequestParam("from") BigDecimal from,
                                                          @RequestParam("to") BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getProductsByCategoryIds(
            @RequestParam(name = "in") List<Long> categoryIds) {
        List<Category> categories = categoryService.findAllByIdIn(categoryIds);
        List<Product> products = productService.findByCategoryIn(categories);
        return products.stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
