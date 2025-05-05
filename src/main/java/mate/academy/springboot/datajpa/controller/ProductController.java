package mate.academy.springboot.datajpa.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.RequestProductDto;
import mate.academy.springboot.datajpa.dto.response.ResponseProductDto;
import mate.academy.springboot.datajpa.mapper.DtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final DtoMapper<RequestProductDto, ResponseProductDto, Product> mapper;
    private final ProductService productService;

    @PostMapping
    public ResponseProductDto create(@RequestParam RequestProductDto productDto) {
        Product product = productService.save(mapper.mapToModel(productDto));
        return mapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ResponseProductDto get(@PathVariable Long id) {
        return mapper.mapToDto(productService.getId(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PatchMapping
    public ResponseProductDto update(@RequestBody @Valid RequestProductDto dto) {
        return mapper.mapToDto(productService.update(mapper.mapToModel(dto)));
    }

    @GetMapping("/price")
    List<ResponseProductDto> getAllByPriceBetween(@RequestParam @Positive BigDecimal from,
                                                  @RequestParam @Positive BigDecimal to) {
        return productService.getAllByPriceBetween(from, to).stream()
                .map(mapper::mapToDto)
                .toList();
    }

    @GetMapping("/categories")
    List<ResponseProductDto> getAllByCategoryIn(@RequestParam List<Category> categories) {
        return productService.getAllByCategoryIn(categories).stream()
                .map(mapper::mapToDto)
                .toList();
    }
}
