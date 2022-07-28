package org.example.controllers;

import org.example.models.User;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserControllers {
    private final UserService userService;

    public UserControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping() //получаем всех людей из ДАО и выводит на отображение
    public String getAll(Model model) {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            userService.add(new User("bla", 10, "@1"));
            userService.add(new User("blabla", 11, "@2"));
            userService.add(new User("bpa", 12, "@3"));
            userService.add(new User("dfbd", 13,  "@4"));
        }
        model.addAttribute("user", users);
    return "user/getAll";
    }
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
    model.addAttribute("user", userService.findById(id));
    return "user/findById";// получем одного человека по айди из ДАО и передаем на отображение
    }
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "user/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "user/new";
        }
        userService.add(user);
        return "redirect:/user";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") Long id) {
        model.addAttribute("user", userService.findById(id));
        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid  User user,BindingResult bindingResult, @PathVariable("id") Long id
                         ) {
        if(bindingResult.hasErrors()) {
            return "user/edit";
        }
        userService.update(id, user);
        return "redirect:/user";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/user";
    }


}

