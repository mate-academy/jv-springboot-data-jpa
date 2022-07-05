package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProductController {
    private ProductService productService;
    private ProductMapper productMapper;
    private CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService,
                             ProductMapper productMapper,
                             CategoryService categoryService) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        return productMapper.toDto(productService.create(productMapper.toModel(requestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto findById(@PathVariable(name = "id") Long id) {
        return productMapper.toDto(productService.get(id));
    }

    @GetMapping("/price")
    public List<ProductResponseDto> findAllByPriceBetween(@RequestParam(name = "from") Double from,
                                      @RequestParam(name = "to") Double to) {
        return productService.findAllByPriceBetween(new BigDecimal(from), new BigDecimal(to))
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<ProductResponseDto> findAllByParams(@RequestParam Map<String, String> params) {
        return productService.findAllByParams(params).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ProductResponseDto update(@PathVariable(name = "id") Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = productService.get(id);
        product.setTitle(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        product.setCategory(categoryService.get(requestDto.getCategoryId()));
        return productMapper.toDto(productService.update(product));
    }
}
