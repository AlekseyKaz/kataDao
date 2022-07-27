package org.example.controllers;

import org.example.DAO.UserDao;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Controller
@RequestMapping("/user")
public class UserControllers {

        private final UserDao userDao;
@Autowired
    public UserControllers(UserDao userDao) {
        this.userDao = userDao;
    }


    @GetMapping()
        public String index(Model model) {
            model.addAttribute("user", userDao.index());
            return "people/index";
        }

        @GetMapping("/{id}")
        public String show(@PathVariable("id") int id, Model model) {
            model.addAttribute("person", userDao.show(id));
            return "people/show";
        }

        @GetMapping("/new")
        public String newPerson(@ModelAttribute("user") User user) {
            return "people/new";
        }

        @PostMapping()
        public String create(@ModelAttribute("person") @Valid User user,
                             BindingResult bindingResult) {
            if (bindingResult.hasErrors())
                return "people/new";

            userDao.save(user);
            return "redirect:/user";
        }

        @GetMapping("/{id}/edit")
        public String edit(Model model, @PathVariable("id") int id) {
            model.addAttribute("person", userDao.show(id));
            return "people/edit";
        }

        @PatchMapping("/{id}")
        public String update(@ModelAttribute("person") @Valid User user, BindingResult bindingResult,
                             @PathVariable("id") int id) {
            if (bindingResult.hasErrors())
                return "people/edit";

            userDao.update(id, user);
            return "redirect:/user";
        }

        @DeleteMapping("/{id}")
        public String delete(@PathVariable("id") int id) {
            userDao.delete(id);
            return "redirect:/user";
        }

}
