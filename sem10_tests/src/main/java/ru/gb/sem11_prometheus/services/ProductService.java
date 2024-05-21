package ru.gb.sem11_prometheus.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.sem11_prometheus.aspects.TrackUserAction;
import ru.gb.sem11_prometheus.domain.Product;
import ru.gb.sem11_prometheus.exceptions.ProductNotFoundException;
import ru.gb.sem11_prometheus.exceptions.WrongProductException;
import ru.gb.sem11_prometheus.repository.ProductRepository;

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
        return prodRep.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @TrackUserAction
    public Product updateProduct(Product updatedProduct) {
        return prodRep.save(updatedProduct);
    }

    @TrackUserAction
    public void addNewProduct(Product newProd) {
        if (!newProd.getName().isEmpty()
                && newProd.getCount() != null
                && newProd.getPrice() != 0)
            prodRep.save(newProd);
        else
            throw new WrongProductException();
    }

    @TrackUserAction
    public void deleteProductById(Long id){
        prodRep.deleteById(id);
    }
}
