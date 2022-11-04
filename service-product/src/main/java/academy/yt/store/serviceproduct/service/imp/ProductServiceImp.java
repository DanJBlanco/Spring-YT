package academy.yt.store.serviceproduct.service.imp;

import academy.yt.store.serviceproduct.entity.Category;
import academy.yt.store.serviceproduct.entity.Product;
import academy.yt.store.serviceproduct.repository.ProductRepository;
import academy.yt.store.serviceproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("CREATED");
        product.setCreateAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {

        Product productDB = getProduct(product.getId());
        if(Objects.isNull(productDB)) return null;

        productDB.setName(product.getName());
        productDB.setCategory(product.getCategory());
        productDB.setDescription(product.getDescription());
        productDB.setPrice(product.getPrice());
        return productRepository.save(productDB);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDB = getProduct(id);
        if(Objects.isNull(productDB)) return null;

        productDB.setStatus("DELETED");
        return productRepository.save(productDB);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {

        Product productDB = getProduct(id);
        if(Objects.isNull(productDB)) return null;

        Double stock = productDB.getStock() + quantity;
        productDB.setStock(stock);

        return productRepository.save(productDB);
    }

}
