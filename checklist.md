## Common mistakes (jv-springboot-data-jpa)

#### Do not create custom queries, use JPA methods
If you are trying to write custom query using `@Query` annotation, think how you can do the same using JPA method naming.

#### REST endpoint naming
Follow best practices when choosing names for your endpoints.
Take a look at this article: https://nordicapis.com/10-best-practices-for-naming-api-endpoints/

#### Use abstract types for mappers
Follow Dependency Inversion principle. Create a parametrized interface (or two separate interfaces for request and respond) for DTO mapping. 
Use interface as type when you declare mapper fields in controllers.
    
 * Bad example:
     ```
        @RestController
        public class ProductController {     
            private final ProductDtoMapper productMapper;
    
        }
    ```
 * Improved example: 
     ```
        @RestController
        public class ProductController {     
            private final DtoMapper<Product, ProductRequestDto, ProductResponceDto> productMapper;
                    
        }
    ```
#### Lombok (in case you use it)
Lombok is a tool that makes our code cleaner and easier to read.

You can use Lombok annotation to generate constructors, getters, setters and other things. 

Take a look at these annotations (great source - https://projectlombok.org/features/): 
1. `@Data`
2. `@Getter`
3. `@Setter`
4. `@ToString`
5. `@EqualsAndHashcode`
6. `@NoArgsConstructor`
7. `@RequiredArgsConstructor`
9. `@AllArgsConstructor`

**BUT**, it should be used with caution, because it can cause performance issues. 
Consider these articles: 
1. https://habr.com/ru/company/haulmont/blog/564682/
2. https://thorben-janssen.com/lombok-hibernate-how-to-avoid-common-pitfalls
