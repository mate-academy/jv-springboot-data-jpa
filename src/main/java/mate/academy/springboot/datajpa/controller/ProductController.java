package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.ProductDto;
import mate.academy.springboot.datajpa.mapper.ProductMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryServiceImp;
import mate.academy.springboot.datajpa.service.ProductServiceImp;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImp productService;
    private final CategoryServiceImp categoryService;
    private final ProductMapper mapper;

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto dto) {
        String categoryName = dto.getCategoryName();
        Category category = categoryService.findByName(categoryName)
                .orElse(new Category().setName(categoryName));
        Product newProduct = mapper.mapToEntity(dto);
        newProduct.setCategory(category);
        Product product = productService.create(newProduct);
        ProductDto resultDto = mapper.mapToDto(product);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable Long id) {
        ProductDto resultDto = productService.findById(id).map(mapper::mapToDto).orElse(null);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductDto dto) {
        Product updatedProduct;
        Category category = categoryService.findByName(dto.getCategoryName()).orElse(null);
        if (category == null) {
            updatedProduct = null;
        } else {
            Product product = mapper.mapToEntity(dto).setCategory(category);
            updatedProduct = productService.update(id, product);
        }
        ProductDto resultDto = mapper.mapToDto(updatedProduct);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/price/between")
    public ResponseEntity<List<ProductDto>> getByPriceBetween(
            @RequestParam Integer lowerPrice,
            @RequestParam Integer higherPrice) {
        List<ProductDto> resultList = productService
                .getByPriceBetween(lowerPrice, higherPrice).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ProductDto>> getByCategories(
            @RequestParam List<String> names) {
        List<Category> categories = names.stream()
                .map(categoryService::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        List<ProductDto> resultList = productService.getByCategories(categories).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}
