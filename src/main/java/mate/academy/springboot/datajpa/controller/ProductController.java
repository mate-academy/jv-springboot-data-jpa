package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
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
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto requestDto) {
        Product product = productMapper.mapToModel(requestDto);
        productService.add(product);
        return productMapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productMapper.mapToDto(productService.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                      @RequestBody ProductRequestDto requestDto) {
        Product product = productMapper.mapToModel(requestDto);
        product.setId(id);
        productService.update(product);
        return productMapper.mapToDto(product);
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getAllPriceBetween(@RequestParam BigDecimal from,
                                                       @RequestParam BigDecimal to) {
        return productService.getAllByPriceBetween(from, to)
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category")
    public List<ProductResponseDto> getAllCategoryIn(@RequestParam List<Long> categoriesId) {
        return productService.getAllByCategories(categoriesId)
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public void saveProducts() {
        Product iphone8 = new Product();
        iphone8.setTitle("Iphone 8");
        iphone8.setPrice(BigDecimal.valueOf(699));
        iphone8.setCategory(categoryService.get(1L));
        productService.add(iphone8);

        Product iphone9 = new Product();
        iphone9.setTitle("Iphone 9");
        iphone9.setPrice(BigDecimal.valueOf(799));
        iphone9.setCategory(categoryService.get(1L));
        productService.add(iphone9);

        Product macBook8 = new Product();
        macBook8.setTitle("MacBook 8");
        macBook8.setPrice(BigDecimal.valueOf(1699));
        macBook8.setCategory(categoryService.get(2L));
        productService.add(macBook8);

        Product macBook9 = new Product();
        macBook9.setTitle("MacBook 9");
        macBook9.setPrice(BigDecimal.valueOf(1799));
        macBook9.setCategory(categoryService.get(2L));
        productService.add(macBook9);
    }
}
