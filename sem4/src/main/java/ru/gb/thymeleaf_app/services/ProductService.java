package ru.gb.thymeleaf_app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.thymeleaf_app.domain.Product;
import ru.gb.thymeleaf_app.repository.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository prodRep;

    public List<Product> getAllProducts() {
        return prodRep.getAllProducts();
    }

    public Product getProductById(Long id) {
        return prodRep.getProductById(id);
    }

    public Product updateProduct(Product updatedProduct) {
        return prodRep.updateProduct(updatedProduct);
    }

    public void addNewProduct(Product newProd) {
        prodRep.addProduct(newProd);
    }

    public void deleteProductById(Long id){
        prodRep.deleteProductById(id);
    }
}
