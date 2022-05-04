package com.camphor.springbootproject.controller;

import com.camphor.springbootproject.entity.User;
import com.camphor.springbootproject.service.UserNoFoundException;
import com.camphor.springbootproject.service.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepositoryService service;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model) {
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra) {
        service.save(user);

        ra.addFlashAttribute("message", "The user has been saved successfully!");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = service.get(id);
            model.addAttribute("newUser", user);
            model.addAttribute("pageTitle", "Edit User (ID: "+ id + ")");
            return "user_form";
        } catch (UserNoFoundException e) {
            e.printStackTrace();
            ra.addFlashAttribute("message", "The user has been saved successfully!");
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
        service.delete(id);
        ra.addFlashAttribute("message", "The user ID "+ id + " has been deleted!");
        } catch (UserNoFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }


}
