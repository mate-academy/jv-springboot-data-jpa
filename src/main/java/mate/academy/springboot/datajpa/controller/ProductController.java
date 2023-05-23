package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
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

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final DtoMapper<ProductRequestDto, Product, ProductResponseDto> productDtoMapper;
    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productDtoMapper.mapToDto(productService.get(id));
    }

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto productRequestDto) {
        return productDtoMapper.mapToDto(productService
                .save(productDtoMapper.mapToModel(productRequestDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                      @RequestBody ProductRequestDto productRequestDto) {
        Product product = productDtoMapper.mapToModel(productRequestDto);
        product.setId(id);
        return productDtoMapper.mapToDto(productService.save(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findAllByPriceBetween(@RequestParam BigDecimal from,
                                                          @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(e -> productDtoMapper.mapToDto(e))
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> findAllByCategories(@RequestParam("category")
                                                            List<String> categories) {
        return productService.findAllByCategoryNameIn(categories).stream()
                .map(productDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
