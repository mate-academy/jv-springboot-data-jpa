package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.ProductRequest;
import mate.academy.springboot.datajpa.dto.ProductResponse;
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

    @PostConstruct
    public void init() {
        Category meat = new Category().setName("meat");
        Category milk = new Category().setName("milk");
        Category bread = new Category().setName("bread");
        categoryService.create(meat);
        categoryService.create(milk);
        categoryService.create(bread);
        Product firstMeat = new Product().setCategory(meat).setPrice(15).setTitle("Fresh");
        Product secondMeat = new Product().setCategory(meat).setPrice(25).setTitle("Middle");
        Product firstMilk = new Product().setCategory(milk).setPrice(40).setTitle("Prostokvash");
        Product secondMilk = new Product().setCategory(milk).setPrice(50).setTitle("Yagotin");
        Product firstBread = new Product().setCategory(bread).setPrice(15).setTitle("Rudnia");
        productService.create(firstMeat);
        productService.create(secondMeat);
        productService.create(firstMilk);
        productService.create(secondMilk);
        productService.create(firstBread);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        String categoryName = request.getCategoryName();
        Category category = categoryService.findByName(categoryName);
        Product newProduct = mapper.mapToEntity(request);
        newProduct.setCategory(category);
        Product product = productService.create(newProduct);
        ProductResponse response = mapper.mapToDto(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> get(@PathVariable Long id) {
        Product product = productService.getById(id);
        ProductResponse response = product == null ? null : mapper.mapToDto(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {
        Category category = categoryService.findByName(request.getCategoryName());
        Product product = mapper.mapToEntity(request).setCategory(category);
        Product updatedProduct = productService.update(id, product);
        ProductResponse response = mapper.mapToDto(updatedProduct);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/price/between")
    public ResponseEntity<List<ProductResponse>> getByPriceBetween(
            @RequestParam Integer lowerPrice,
            @RequestParam Integer higherPrice) {
        List<ProductResponse> resultList = productService
                .getByPriceBetween(lowerPrice, higherPrice).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ProductResponse>> getByCategories(
            @RequestParam List<String> names) {
        List<Category> categories = names.stream()
                .map(categoryService::findByName)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        List<ProductResponse> resultList = productService.getByCategories(categories).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}
