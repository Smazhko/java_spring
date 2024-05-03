package ru.gb.sem7_security.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.sem7_security.domain.Product;
import ru.gb.sem7_security.repository.ProdRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProdRepository prodRep;

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
        prodRep.save(newProd);
    }

    public void deleteProductById(Long id){
        prodRep.deleteById(id);
    }
}
