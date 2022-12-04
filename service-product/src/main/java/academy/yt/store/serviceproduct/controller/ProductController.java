package academy.yt.store.serviceproduct.controller;

import academy.yt.store.serviceproduct.entity.Category;
import academy.yt.store.serviceproduct.entity.Product;
import academy.yt.store.serviceproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(@Qualifier("productService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name = "categoryId", required = false) Long category){

        List<Product> products;

        if (Objects.isNull(category)){
            products = productService.listAllProduct();
        } else {
            products = productService.findByCategory( Category.builder().id(category).build() );
            if( products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }


        if(products.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/{categoryId}")
    public ResponseEntity<Product> getProduct(@PathVariable("categoryId") Long id){
        Product product = productService.getProduct(id);

        if(Objects.isNull(product)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product productCreate = productService.createProduct(product);

        if(Objects.nonNull(productCreate)){
            return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long id, @RequestBody Product product){
        product.setId(id);
        Product productUpdate = productService.updateProduct(product);


        if(Objects.nonNull(productUpdate)){
            return ResponseEntity.ok(productUpdate);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") Long id){

        Product productDelete = productService.deleteProduct(id);

        if(Objects.nonNull(productDelete)){
            return ResponseEntity.ok(productDelete);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{idProduct}/stock")
    public ResponseEntity<Product> updateStockProduct(
            @PathVariable("idProduct") Long id,
            @RequestParam(value = "quantity", required = true) Double quantity ){

        Product productUpdateStock = productService.updateStock(id, quantity);

        if(Objects.nonNull(productUpdateStock)){
            return ResponseEntity.ok(productUpdateStock);
        }
        return ResponseEntity.notFound().build();

    }
}
