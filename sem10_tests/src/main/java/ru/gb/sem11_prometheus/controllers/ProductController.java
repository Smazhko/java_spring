package ru.gb.sem11_prometheus.controllers;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.sem11_prometheus.domain.Product;
import ru.gb.sem11_prometheus.services.ProductService;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {
    private ProductService prodSrvc;

    private final Counter addProdCounter = Metrics.counter("add_prod_count");

    // ---------------------------------------------
    //@GetMapping(value= {"/", "/index"})
    @GetMapping("/")
    public String showAllProductsToAll(Product newProd, Model model) {
        List<Product> prodList = prodSrvc.getAllProducts();
        model.addAttribute("prodList", prodList);
        return "index";
    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }

    // ---------------------------------------------
    //@GetMapping(value= {"/", "/index"})
    @GetMapping("/user")
    public String showAllProductsToUser(Product newProd, Model model) {
        List<Product> prodList = prodSrvc.getAllProducts();
        model.addAttribute("prodList", prodList);

        return "user";
    }

    // ---------------------------------------------
    //@GetMapping(value= {"/", "/index"})
    @GetMapping("/admin")
    public String showAllProductsToAdmin(Product newProd, Model model) {
        model.addAttribute("newProduct", newProd);

        List<Product> prodList = prodSrvc.getAllProducts();
        model.addAttribute("prodList", prodList);

        return "admin";
    }

    // --------------------------------------------
    @PostMapping("admin")
    public String addNewProduct(Product newProd, Model model) {
        prodSrvc.addNewProduct(newProd);

        addProdCounter.increment();

        List<Product> prodList = prodSrvc.getAllProducts();
        model.addAttribute("prodList", prodList);

        return "redirect:/admin";
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
    @GetMapping("/product_buy/{id}")
    public String butProductById(@PathVariable Long id) {
        prodSrvc.deleteProductById(id);
        return "redirect:/user";
    }
    // ----------------------------------------------
    @GetMapping("/product_delete/{id}")
    public String deleteProductById(@PathVariable Long id) {
        prodSrvc.deleteProductById(id);
        return "redirect:/admin";
    }

    // ----------------------------------------------
    @GetMapping("product_edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        // получаем объект, который надо отредактировать через ID из запроса
        // помещаем этот объект в МОДЕЛЬ, чтобы на страничке заполнить поля в форме старыми данными
        Product prodToEdit = prodSrvc.getProductById(id);
        model.addAttribute("prodToEdit", prodToEdit);
        return "product_edit";
    }

    // +
    @PostMapping("product_edit/{id}")
    public String editProductById(Product product) {
        prodSrvc.updateProduct(product);
        return "redirect:/admin";
    }

}
