package ru.gb.sem12_integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.sem12_integration.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
