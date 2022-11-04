package academy.yt.store.serviceproduct.service;

import academy.yt.store.serviceproduct.entity.Category;
import academy.yt.store.serviceproduct.entity.Product;
import academy.yt.store.serviceproduct.repository.ProductRepository;
import academy.yt.store.serviceproduct.service.imp.ProductServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.mock;

@SpringBootTest
public class ProductServiceMockTest {

    ProductRepository productRepository;

    ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImp(productRepository);

        Product computer = Product.builder()
                .id(1L)
                .name("computer")
                .category(Category.builder().id(1L).build())
                .price(12.5)
                .stock(5.0)
                .build();

        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(computer));
        Mockito.when(productRepository.save(computer))
                .thenReturn(computer);
    }

    @Test
    void whenValidGetID_ThenReturnProduct() {
        Product found = productService.getProduct(1L);

        Assertions.assertEquals(found.getName(), "computer");

    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock() {

        Product found = productService.updateStock(1L, 6.9);

        Assertions.assertEquals(found.getStock(), 11.9);
    }
}
