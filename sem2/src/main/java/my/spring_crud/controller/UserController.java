package my.spring_crud.controller;

import my.spring_crud.model.User;
import my.spring_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Метод выполняется при запросе браузера результатов по адресу localhost:8080/users.
     * Формируется список пользователей, который передаётся в модель,
     * чтобы в последующем thymeleaf мог взять эти данные для формирования таблицы html
     * @param model
     * @return страница user-list.html, которая формируется по шаблону
     */
    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();

        model.addAttribute("users", users);
        return "user-list";
    }

    /**
     * создание пользователя состоит из двух частей - GET запрос и POST запрос.
     * ТУТ - первая часть - GET запрос. Он отправляет на страницу создания нового пользователя.
     * @param user - объект типа USER, в который будут помещаться данные из формы.
     * @return страница с формой для заполнения данных нового пользователя user-create.html
     */
    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    /**
     * Создание пользователя состоит из двух частей - GET запрос и POST запрос.
     * ТУТ - вторая часть - POST запрос. Он вызывается на странице нажатием кнопки СОЗДАТЬ - формируется запрос.
     * У СЕРВИСА вызывается метод добавления нового пользователя в базу.
     * Далее происходит перенаправление браузера на страницу со всеми пользователями.
     * @param user
     * @return перенаправление на страницу со всеми пользователями
     */
    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    /**
     * Удаление пользователя из базы - GET запрос, в котором передаётся ID пользователя для удаления.
     * Для того, чтобы SPRING считал ID пользователя из адреса, применяем аннотацию @PathVariable.
     * Ситалнную переменную отправляем дальше по методам сервиса и репозитория.
     * @param id
     * @return перенаправление на страницу всех пользователей
     */
    @GetMapping("/user-delete/{id}")
    public String deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    /**
     * Редактирование уже имеющегося пользователя.
     * По аналогии с SAVE процесс редактирования состоит из двух методов - GET и POST запрос.
     * ТУТ - перенаправления на страницу редактирования.
     * Не используем аннотацию @PathVariable, так как для редактирования она не нужна.
     * {id} из адреса используется в шаблоне user-update при заполнении в форме нередактируемого поля ID.
     * @param newUser - объект типа USER для того, чтобы принять в него новые данные из формы.
     * @return
     */
    @GetMapping("user-update/{id}")
    public String updateUserForm(User newUser){
        return "user-update";
    }


    /**
     * Редактирование уже имеющегося пользователя.
     * ТУТ - вторая часть. У СЕРВИСА вызывается метод редактирования пользователя,
     * который в свою очередь вызывает метод редактирования у РЕПОЗИТОРИЯ.
     * По методоам передаётся объект типа USER, который содержит новые данные о пользователе.
     * Так как в форме поле ID заполнено, то при нажатии кнопки СОХРАНИТЬ создаётся объект USER
     * с заполненными полями не только login и password, но и id. Поэтому в методе UPDATE у РЕПОЗИТОРИЯ
     * мы берем данные из созданного объекта и добавляем все необходимые параметры в SQL запрос.
     * @param newUser
     * @return
     */
    @PostMapping("user-update/{id}")
    public String updateUserById(User newUser){
        userService.updateUser(newUser);
        return "redirect:/users";
    }


}
