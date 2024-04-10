package my.spring_crud.repository;

import my.spring_crud.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    // автоматическое подключение драйвера Н2
    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
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
            rowObject.setId(r.getInt("id"));
            rowObject.setLogin(r.getString("login"));
            rowObject.setPassword(r.getString("password"));
            return rowObject;
        };
        return jdbc.query(sql, userRowMapper);
    }

    /**
     * Сохранение нового пользователя в базу
     * @param user
     * @return
     */
    public User save(User user) {
        String sql = "INSERT INTO userTable (login, password) VALUES (?, ?)";
        jdbc.update(sql, user.getLogin(), user.getPassword());
        return user;
    }

    /**
     * Удаление пользователя из базы по id
     * @param id
     */
    public void deleteUserById(Integer id) {
        String sql = "DELETE FROM userTable WHERE id = ?";
        jdbc.update(sql, id);
    }

    /**
     * Внесение новых данных о пользователе в базу
     * @param newUser - объект User с заполненными полями
     * @return newUser
     */
    public User updateUser(User newUser) {
        String sql = "UPDATE userTable SET login = ?, password = ? WHERE id = ?";
        jdbc.update(sql, newUser.getLogin(), newUser.getPassword(), newUser.getId());
        return newUser;
    }
}
