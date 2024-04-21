package ru.gb.thymeleaf_app.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.gb.thymeleaf_app.domain.Product;

import java.util.List;

@Repository
// подключение зависимости через конструктор
@AllArgsConstructor
public class ProductRepository {
    // автоматическое подключение драйвера Н2
    private final JdbcTemplate jdbc;

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM productTable;";
//        Создается объект RowMapper<User>.
//        @FunctionalInterface
//        public interface RowMapper<T> {
//              @Nullable
//              T mapRow(ResultSet rs, int rowNum) throws SQLException; }
//        RowMapper - это интерфейс, предоставляемый Spring JDBC,
//        который используется для сопоставления строк из результата запроса с объектами Java.
//        В данном случае, создается анонимный класс, который реализует интерфейс RowMapper<User>.
//        Метод mapRow() этого класса извлекает данные из результирующей строки ResultSet и создает
//        объект User на основе этих данных. Метод getInt() извлекает значение типа int из столбца
//        с указанным именем, а метод getString() извлекает значение типа String. Затем, эти значения
//        устанавливаются в поля объекта User. Наконец, созданный объект User возвращается.
        RowMapper<Product> productRowMapper = (resultSet, rowNum) -> {
            Product rowObject = new Product();
            rowObject.setId(resultSet.getLong("id"));
            rowObject.setName(resultSet.getString("name"));
            rowObject.setPrice(resultSet.getDouble("price"));
            rowObject.setCount(resultSet.getInt("prod_count"));
            return rowObject;
        };
        return jdbc.query(sql, productRowMapper);
    }

    public Product addProduct(Product prod) {
        String sql = "INSERT INTO productTable (name, price, prod_count) VALUES (?, ?, ?)";
        jdbc.update(sql, prod.getName(), prod.getPrice(), prod.getCount());
        return prod;
    }

    public void deleteProductById(Long id) {
        String sql = "DELETE FROM productTable WHERE id = ?";
        jdbc.update(sql, id);
    }

    public Product updateProduct(Product newProd) {
        String sql = "UPDATE productTable SET name = ?, price = ?, prod_count = ? WHERE id = ?";
        jdbc.update(sql, newProd.getName(), newProd.getPrice(), newProd.getCount(), newProd.getId());
        return newProd;
    }
    
    public Product getProductById(Long id){
//        В этом методе мы выполняем SQL-запрос для выборки продукта из таблицы по его идентификатору.
//        Мы используем queryForObject(), который возвращает один объект, а не список объектов,
//        и передаем идентификатор в качестве параметра для поиска конкретного продукта.
//        Метод queryForObject() автоматически маппит результат запроса на объект Product с помощью RowMapper.
        String sql = "SELECT * FROM productTable WHERE id = ?;";
        RowMapper<Product> productRowMapper = (resultSet, rowNum) -> {
            Product rowObject = new Product();
            rowObject.setId(resultSet.getLong("id"));
            rowObject.setName(resultSet.getString("name"));
            rowObject.setPrice(resultSet.getDouble("price"));
            rowObject.setCount(resultSet.getInt("prod_count"));
            return rowObject;
        };
        return jdbc.queryForObject(sql, productRowMapper, id);
    }
}
