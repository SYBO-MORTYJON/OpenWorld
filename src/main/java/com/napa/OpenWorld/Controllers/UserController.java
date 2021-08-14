package com.napa.OpenWorld.Controllers;

import com.napa.OpenWorld.entities.User;
import com.napa.OpenWorld.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.regex.Pattern;

@Controller
public class UserController {
    @Autowired
    UserServise userServise;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "user/registration";
    }

    @PostMapping("/registration")
    public String registrationValidator(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult){
        if (!userForm.getPassword().equals(userForm.getConfirm())){
            bindingResult.rejectValue("confirm", "confirm", "Parollar har xil");
        }
        Pattern pattern = Pattern.compile("^[a-z]+[-a-z0-9]+$");
        if (!pattern.matcher(userForm.getUsername()).find()){
            bindingResult.rejectValue("username", "username", "Login faqat lotin harflari va sonlardan iborat bo`lishi shart.");
        }
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "user/registration";
        }
        if (!userServise.saveUser(userForm)){
            bindingResult.rejectValue("username", "username", "Ushbu login band.");
            return "user/registration";
        }
        return "redirect:/";
    }
}
