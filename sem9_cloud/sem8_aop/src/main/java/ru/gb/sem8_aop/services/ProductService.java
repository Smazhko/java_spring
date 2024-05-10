package ru.gb.sem8_aop.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.sem8_aop.aspects.TrackUserAction;
import ru.gb.sem8_aop.domain.Product;
import ru.gb.sem8_aop.repository.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository prodRep;

    @TrackUserAction
    public List<Product> getAllProducts() {
        return prodRep.findAll();
    }

    @TrackUserAction
    public Product getProductById(Long id) {
        return prodRep.findById(id).orElseThrow();
    }

    @TrackUserAction
    public Product updateProduct(Product updatedProduct) {
        return prodRep.save(updatedProduct);
    }

    @TrackUserAction
    public void addNewProduct(Product newProd) {
        if (!newProd.getName().isEmpty()
                && newProd.getCount() != null
                && newProd.getPrice() != 0) prodRep.save(newProd);
    }

    @TrackUserAction
    public void deleteProductById(Long id){
        prodRep.deleteById(id);
    }
}
