package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductService productService;

    public ProductController(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto save(@RequestBody @Valid ProductRequestDto requestDto) {
        Product product = productService.save(productMapper.dtoToModel(requestDto));
        return productMapper.modelToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productMapper.modelToDto(productService.get(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam @PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id,
                                         @RequestBody @Valid ProductRequestDto requestDto) {
        Product product = productMapper.dtoToModel(requestDto);
        product.setId(id);
        int result = productService.update(product);
        return result > 0 ? new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getAllProductsBetweenPrice(@RequestParam BigDecimal priceFrom,
                                                               @RequestParam BigDecimal priceTo) {
        return productService.getAllBetweenPrice(priceFrom, priceTo).stream()
               .map(productMapper::modelToDto)
               .collect(Collectors.toList());
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> getAllProductsByCategories(
            @RequestParam List<Long> categories) {
        return productService.getAllProductsByCategories(categories).stream()
                .map(productMapper::modelToDto)
                .collect(Collectors.toList());
    }
}
