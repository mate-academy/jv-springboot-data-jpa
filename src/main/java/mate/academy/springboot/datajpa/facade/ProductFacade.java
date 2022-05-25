package mate.academy.springboot.datajpa.facade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.ProductDto;
import mate.academy.springboot.datajpa.mapper.ProductMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductServiceImp;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductFacade extends BaseFacade<ProductDto> {

    private final ProductServiceImp service;
    private final CategoryService categoryService;
    private final ProductMapper mapper;

    @Override
    public ProductDto create(ProductDto dto) {
        String categoryName = dto.getCategoryName();
        Category category = categoryService.findByName(categoryName)
                .orElse(new Category().setName(categoryName));
        Product newProduct = mapper.mapToEntity(dto);
        newProduct.setCategory(category);
        Product product = service.create(newProduct);
        return mapper.mapToDto(product);
    }

    @Override
    public ProductDto findById(Long id) {
        return service.findById(id).map(mapper::mapToDto).orElse(null);
    }

    @Override
    public ProductDto update(Long id, ProductDto dto) {
        Category category = categoryService.findByName(dto.getCategoryName()).orElse(null);
        if (category == null) {
            return null;
        }
        Product product = mapper.mapToEntity(dto).setCategory(category);
        Product updatedProduct = service.update(id, product);
        return updatedProduct == null
            ? null
            : mapper.mapToDto(updatedProduct);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    public List<ProductDto> getByPriceBetween(Integer lowerPrice, Integer higherPrice) {
        return service.getByPriceBetween(lowerPrice, higherPrice).stream()
            .map(mapper::mapToDto)
            .collect(Collectors.toList());
    }

    public List<ProductDto> getByCategories(List<String> categoryNames) {
        List<Category> categories = categoryNames.stream()
                .map(categoryService::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        return service.getByCategories(categories)
            .stream()
            .map(mapper::mapToDto)
            .collect(Collectors.toList());
    }
}
