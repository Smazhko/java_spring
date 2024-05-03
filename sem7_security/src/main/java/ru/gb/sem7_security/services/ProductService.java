package ru.gb.sem7_security.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.sem7_security.domain.Product;
import ru.gb.sem7_security.repository.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository prodRep;

    public List<Product> getAllProducts() {
        return prodRep.findAll();
    }

    public Product getProductById(Long id) {
        return prodRep.findById(id).orElseThrow();
    }

    public Product updateProduct(Product updatedProduct) {
        return prodRep.save(updatedProduct);
    }

    public void addNewProduct(Product newProd) {
        if (!newProd.getName().isEmpty()
                && newProd.getCount() != null
                && newProd.getPrice() != 0) prodRep.save(newProd);
    }

    public void deleteProductById(Long id){
        prodRep.deleteById(id);
    }
}
