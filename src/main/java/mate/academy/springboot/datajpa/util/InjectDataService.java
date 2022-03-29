package mate.academy.springboot.datajpa.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.PostConstruct;
import lombok.Data;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Component;

@Data
@Component
public class InjectDataService {
    private final ProductService productService;
    private final CategoryService categoryService;
    private Map<String,String> productMap = new HashMap<>();

    public InjectDataService(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostConstruct
    public void init() {
        productMap.put("Iphone", "Phones");
        productMap.put("Samsung", "Phones");
        productMap.put("Tigernu", "Backpacks");
        productMap.put("Jacobs", "Caffe");
        productMap.put("Burai", "Watches");

    }

    public void inject(int count) {
        List<Product> products = new ArrayList<>();
        String[] titles = {"Iphone", "Samsung", "Tigernu", "Jacobs", "Burai"};
        List<Category> categories = saveCategories();

        for (int i = 0; i < count; i++) {
            Product product = new Product();
            String productName = titles[new Random().nextInt(titles.length)];
            final String title = productName
                    + " "
                    + getModel(productName);

            product.setTitle(title);
            product.setPrice(BigDecimal.valueOf(getPrice(productName)));
            product.setCategory(getCategory(categories, productName));

            if (!products.contains(product)) {
                products.add(product);
            } else {
                i--;
            }
        }

        saveProducts(products);
    }

    public List<Category> saveCategories() {
        Category[] categories = {new Category("Phones"),
                new Category("Backpacks"),
                new Category("Caffe"),
                new Category("Watches")};
        Arrays.stream(categories).forEach(categoryService::save);

        return categoryService.getAll();
    }

    public void saveProducts(List<Product> products) {
        products.forEach(productService::save);
    }

    public String getModel(String productName) {
        final String[] iphoneModels = {"XR", "11", "12", "13"};
        final String[] samsungModels = {"A52", "S20", "S21", "M52"};
        final String[] tigernuModels = {"R11", "M28", "VAT17", "W2"};
        final String[] buraiModels = {"AC23", "AC55", "AC7", "FG12"};

        switch (productName) {
            case "Iphone":
                return iphoneModels[new Random().nextInt(iphoneModels.length)];
            case "Samsung":
                return samsungModels[new Random().nextInt(samsungModels.length)];
            case "Tigernu":
                return tigernuModels[new Random().nextInt(tigernuModels.length)];
            case "Jacobs":
                return "Monarch";
            case "Burai":
                return buraiModels[new Random().nextInt(buraiModels.length)];
            default:
                return String.valueOf(new Random().nextInt(100));
        }
    }

    public int getPrice(String productName) {
        int max;
        int min;

        switch (productName) {
            case "Iphone":
                max = 1500;
                min = 400;
                break;
            case "Samsung":
                max = 1200;
                min = 200;
                break;
            case "Tigernu":
                max = 500;
                min = 10;
                break;
            case "Jacobs":
                max = 100;
                min = 5;
                break;
            case "Burai":
                max = 100;
                min = 1000;
                break;
            default:
                max = 0;
                min = 0;
        }
        int randomValue = new Random().nextInt(max);
        return Math.max(randomValue, min);
    }

    public Category getCategory(List<Category> categories, String productName) {
        return categories.stream()
                .filter(c -> c.getName().equals(productMap.get(productName)))
                .findFirst().orElseThrow(() -> new RuntimeException("Invalid product name"));
    }
}
