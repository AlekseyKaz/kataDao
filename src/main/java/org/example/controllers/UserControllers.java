package org.example.controllers;

import org.example.DAO.UserDao;
import org.example.DAO.UserDaoImpl;
import org.example.models.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserControllers {
    private final UserService userService;

    public UserControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user") //получаем всех людей из ДАО и выводит на отображение
    public String getAll(Model model) {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            userService.add(new User("bla", 10, "@1"));
            userService.add(new User("blabla", 11, "@2"));
            userService.add(new User("bpa", 12, "@3"));
            userService.add(new User("dfbd", 13,  "@4"));
        }
        model.addAttribute("user", users);
    return "/getAll";
    }
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
    model.addAttribute("user", userService.findById(id));
    return "findById";// получем одного человека по айди из ДАО и передаем на отображение
    }

}
