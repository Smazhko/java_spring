package ru.gb.thymeleaf_app.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.thymeleaf_app.domain.Product;
import ru.gb.thymeleaf_app.services.ProductService;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {
    private ProductService prodSrvc;

    // ---------------------------------------------
    @GetMapping("products")
    public String showAllProducts(Product newProd, Model model) {
        model.addAttribute("newProduct", newProd);

        List<Product> prodList = prodSrvc.getAllProducts();
        model.addAttribute("prodList", prodList);

        return "products";
    }

    // --------------------------------------------
    @PostMapping("products")
    public String addNewProduct(Product newProd, Model model) {
        prodSrvc.addNewProduct(newProd);

        List<Product> prodList = prodSrvc.getAllProducts();
        model.addAttribute("prodList", prodList);

        return "redirect:/products";
    }

    // ----------------------------------------------
    @GetMapping("{id}")
    public String showProductById(Model model, @PathVariable Long id) {
        Product prod = prodSrvc.getProductById(id);
        model.addAttribute("product", prod);
//        model.addAttribute("id", prod.getId());
//        model.addAttribute("name", prod.getName());
//        model.addAttribute("product", prod.getPrice());
//        model.addAttribute("product", prod.getCount());
        return "product";
    }

    // ----------------------------------------------
    @GetMapping("product_delete/{id}")
    public String deleteProductById(@PathVariable Long id) {
        prodSrvc.deleteProductById(id);
        return "redirect:/products";
    }

    // ----------------------------------------------
    @GetMapping("product_edit/{id}")
    public String updateUserForm(@PathVariable Long id, Model model) {
        Product prodToEdit = prodSrvc.getProductById(id);
        model.addAttribute("prodToEdit", prodToEdit);
        return "product_edit";
    }

    // +
    @PostMapping("product_edit/{id}")
    public String editProductById(Product product) {
        prodSrvc.updateProduct(product);
        return "redirect:/products";
    }

}
