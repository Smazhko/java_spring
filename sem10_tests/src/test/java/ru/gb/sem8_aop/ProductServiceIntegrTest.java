package ru.gb.sem8_aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.sem8_aop.domain.Product;
import ru.gb.sem8_aop.exceptions.WrongProductException;
import ru.gb.sem8_aop.repository.ProductRepository;
import ru.gb.sem8_aop.services.ProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceIntegrTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository repository;

    @Test
    public void getAllProductsTest() {
        //Блок предусловия//........................
        Product prod1 = new Product(11L, "Кроссовки", 5000.0, 10);
        Product prod2 = new Product(12L, "Свитшот", 4000.0, 5);

        when(repository.findAll()).thenReturn(List.of(prod1, prod2));
        //........................

        //Блок действия (вызова метода)//........................
        var result = productService.getAllProducts();
        //........................

        //Блок проверки действия//........................
        assertEquals(List.of(prod1, prod2), result);
    }

    @Test
    public void addProdWithNullCountTest() {
        //Блок предусловия//........................
        Product prod1 = new Product(1L, "Кроссовки", 5000.0, null);

        //Блок действия (вызова метода)//........................
        //Мы предполагаем, что для данного варианта выполнения метод
        //должен выбрасывать исключение
        assertThrows(WrongProductException.class, () -> {
            productService.addNewProduct(prod1);
        });

        //Блок проверки действия//........................
        //Используем метод verify() с условием never() для уверенности, что метод
        //changeAmount() не вызывается
        verify(repository, never()).save(any());
    }

    @Test
    public void addProdWithZeroPriceTest() {
        //Блок предусловия//........................
        Product prod1 = new Product(1L, "Кроссовки", 0.0, 10);

        //Блок действия (вызова метода)//........................
        //Мы предполагаем, что для данного варианта выполнения метод
        //должен выбрасывать исключение
        assertThrows(WrongProductException.class, () -> {
            productService.addNewProduct(prod1);
        });

        //Блок проверки действия//........................
        //Используем метод verify() с условием never() для уверенности, что метод
        //changeAmount() не вызывается
        verify(repository, never()).save(any());
    }
}
