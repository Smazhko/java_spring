package ru.gb.sem11_prometheus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.gb.sem11_prometheus.domain.Product;
import ru.gb.sem11_prometheus.exceptions.WrongProductException;
import ru.gb.sem11_prometheus.repository.ProductRepository;
import ru.gb.sem11_prometheus.services.ProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository repository;

    @Test
    public void getAllProductsTest() {
        //Блок предусловия//........................
        Product prod1 = new Product(1L, "Кроссовки", 5000.0, 10);
        Product prod2 = new Product(2L, "Свитшот", 4000.0, 5);

        given(repository.findAll()).willReturn(List.of(prod1, prod2)); // ЗАГЛУШКА РАБОТЫ РЕПОЗИТОРИЯ
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
