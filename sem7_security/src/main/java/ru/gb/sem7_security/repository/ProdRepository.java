package ru.gb.sem7_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.sem7_security.domain.Product;

@Repository
public interface ProdRepository extends JpaRepository<Product, Long> {

}
