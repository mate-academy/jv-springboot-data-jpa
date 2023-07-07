package mate.academy.springboot.datajpa.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.exception.DbException;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.entity.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
import org.hibernate.PropertyValueException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final DtoMapper<Product, ProductResponseDto, ProductRequestDto> mapper;

    @PostMapping
    public ProductResponseDto saveProduct(@RequestBody ProductRequestDto requestDto) {
        return mapper.mapToDto(productService.save(mapper.mapToEntity(requestDto)));
    }

    @GetMapping
    public ProductResponseDto getProductById(@RequestParam Long id) {
        return mapper.mapToDto(productService.findById(id));
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam Long id) {
        productService.delete(id);
    }

    @PutMapping
    public ProductResponseDto update(@RequestParam Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = mapper.mapToEntity(productRequestDto);
        product.setId(id);
        return mapper.mapToDto(productService.update(product));
    }

    @GetMapping("/price")
    public List<ProductResponseDto> findAllByPriceBetween(@RequestParam double from,
                                                          @RequestParam double to) {
        return productService.findAllWherePriceBetween(from, to).stream()
                .map(mapper::mapToDto)
                .toList();
    }

    @GetMapping("/category")
    public List<ProductResponseDto> findAllByCategoryName(@RequestParam List<String> name) {
        return productService.findAllByCategoryNameIn(name).stream()
                .map(mapper::mapToDto)
                .toList();
    }

    @ExceptionHandler(value = DbException.class)
    public String handleDbException(DbException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = PropertyValueException.class)
    public String handlePropertyValueException(PropertyValueException ex) {
        return "Can`t add product to database. "
                + "Field \"" + ex.getPropertyName() + "\" should not be null";
    }
}
