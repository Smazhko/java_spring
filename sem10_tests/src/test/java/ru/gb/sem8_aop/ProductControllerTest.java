package ru.gb.sem8_aop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import ru.gb.sem8_aop.controllers.ProductController;
import ru.gb.sem8_aop.domain.Product;
import ru.gb.sem8_aop.services.ProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private Model model;

    @Mock
    private ProductService productService;

    @InjectMocks
    ProductController productController;

    @Test
    public void addNewProductTest (){
        Product newProd = new Product(1L, "джинсы", 7000.0, 8);

        given(productService.getAllProducts()).willReturn(List.of(newProd));

        String result = productController.addNewProduct(newProd, model);

        assertEquals("redirect:/admin", result);

        verify(model).addAttribute("prodList", List.of(newProd));

    }

}
