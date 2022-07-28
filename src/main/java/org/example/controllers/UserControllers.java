package org.example.controllers;

import org.example.service.UserService;
import org.example.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.GeneratedValue;

@Controller
@RequestMapping("/user")
public class UserControllers {
    private final UserService userService;
@Autowired
    public UserControllers(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user") //получаем всех людей из ДАО и выводит на отображение
    public String getAll(Model model) {
    model.addAttribute("user", userService.getAll());
    return "getAll";
    }
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
    model.addAttribute("user", userService.findById(id));
    return "findById";// получем одного человека по айди из ДАО и передаем на отображение
    }

}
