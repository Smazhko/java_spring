package ru.gb.rest_server.repository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gb.rest_server.domain.User;

import java.util.List;

@Repository
public class UserRepositoryDB implements UserRep{
    // автоматическое подключение драйвера Н2
    private final JdbcTemplate jdbc;

    public UserRepositoryDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<User> getUsers() {
        String sql = "SELECT * FROM userTable;";

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
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setName(r.getString("name"));
            rowObject.setEmail(r.getString("email"));
            rowObject.setAge(r.getInt("age"));
            return rowObject;
        };
        return jdbc.query(sql, userRowMapper);
    }


    @Override
    public User addUser(User newUser) {
        String sql = "INSERT INTO userTable (name, email, age) VALUES (?, ?, ?)";
        jdbc.update(sql, newUser.getName(), newUser.getEmail(), newUser.getAge());
        return newUser;
    }
}
