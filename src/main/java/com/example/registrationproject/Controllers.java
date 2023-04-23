package com.example.registrationproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Controllers {
    @Autowired
    private dao userRepository;

    @GetMapping("/")
    public String showForm(User user) {
        return "index";
    }

    @PostMapping("/")
    public String submitForm(@ModelAttribute("user") User user, Model model) {
        if(userRepository.existsByLogin(user.getLogin())) {
            model.addAttribute("error", "This login already exists.");
            return "index";
        }
        userRepository.save(user);
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String submitLoginForm(@RequestParam("login") String login,
                                  @RequestParam("password") String password,
                                  Model model) {
        User user = userRepository.findByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("login", user.getNick());
            return "welcome";
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }
}
