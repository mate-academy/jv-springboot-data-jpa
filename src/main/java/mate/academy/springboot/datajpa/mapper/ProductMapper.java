package mate.academy.springboot.datajpa.mapper;

import mate.academy.springboot.datajpa.dto.ProductDto;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends BaseMapper<Product, ProductDto> {

    @Override
    public ProductDto mapToDto(Product product) {
        return product == null ? null : new ProductDto()
            .setTitle(product.getTitle())
            .setPrice(product.getPrice())
            .setCategoryName(product.getCategory().getName());
    }

    @Override
    public Product mapToEntity(ProductDto dto) {
        return dto == null ? null : new Product()
            .setTitle(dto.getTitle())
            .setPrice(dto.getPrice());
    }

    @Override
    public Product mapUpdate(Product source, Product target) {
        if (source == null) {
            return null;
        }

        if (source.getId() != null) {
            target.setId(source.getId());
        }
        if (source.getDeleted() != null) {
            target.setDeleted(source.getDeleted());
        }
        if (source.getTitle() != null) {
            target.setTitle(source.getTitle());
        }
        if (source.getPrice() != null) {
            target.setPrice(source.getPrice());
        }
        if (source.getCategory() != null) {
            target.setCategory(source.getCategory());
        }
        return target;
    }
}
