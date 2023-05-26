package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        return productMapper.modelToDto(
                productService.save(
                        productMapper.dtoToModel(productRequestDto)
                )
        );
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id) {
        return productMapper.modelToDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id,
                                            @RequestParam ProductRequestDto productRequestDto) {
        Product product = productMapper.dtoToModel(productRequestDto);
        product.setId(id);
        return productMapper.modelToDto(productService.update(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getProductByPrice(@RequestParam BigDecimal from,
                                                      @RequestParam BigDecimal to) {
        return productService.getByPriceBetween(from, to)
                .stream()
                .map(productMapper::modelToDto)
                .toList();
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> getProductByCategory(
            @RequestParam Map<String, String> categories) {
        return productService.getByCategory(categories)
                .stream()
                .map(productMapper::modelToDto)
                .toList();
    }
}
