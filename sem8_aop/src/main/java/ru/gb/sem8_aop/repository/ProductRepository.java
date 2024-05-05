package ru.gb.sem8_aop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.sem8_aop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
