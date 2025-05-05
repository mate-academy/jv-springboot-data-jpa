package mate.academy.springboot.datajpa.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.RequestProductDto;
import mate.academy.springboot.datajpa.dto.response.ResponseProductDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
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
    private final DtoMapper<RequestProductDto, ResponseProductDto, Product> productDtoMapper;
    private final ProductService productService;

    @PostMapping
    public ResponseProductDto create(@RequestBody RequestProductDto dto) {
        return productDtoMapper.mapToDto(productService.add(productDtoMapper.mapToModel(dto)));
    }

    @GetMapping("/{id}")
    public ResponseProductDto get(@PathVariable Long id) {
        return productDtoMapper.mapToDto(productService.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PatchMapping
    public ResponseProductDto update(@RequestBody @Valid RequestProductDto dto) {
        return productDtoMapper.mapToDto(productService.update(productDtoMapper.mapToModel(dto)));
    }

    @GetMapping("/price")
    List<ResponseProductDto> getAllBetweenPrice(@RequestParam @Positive BigDecimal from,
                                                @RequestParam @Positive BigDecimal to) {
        return productService.getAllBetweenPrice(from, to).stream()
                .map(productDtoMapper::mapToDto)
                .toList();
    }

    @GetMapping("/categories")
    List<ResponseProductDto> getAllInCategories(@RequestParam @NotBlank
                                                List<Category> categories) {
        return productService.getAllInCategories(categories).stream()
                .map(productDtoMapper::mapToDto)
                .toList();
    }
}
