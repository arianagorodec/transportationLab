package com.gorodeckaya.controller;

import com.gorodeckaya.entity.User;
import com.gorodeckaya.service.impl.UserServiceImpl;
import com.gorodeckaya.entity.User;
import com.gorodeckaya.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserServiceImpl userService;


    @PostMapping("/partner_registration")
    public String addPartner(@ModelAttribute("partnerForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        boolean ch = true;
        if (bindingResult.hasErrors()) {
            return "index";
        }
        if(userForm.getCompany().equals("")) {
            model.addAttribute("companyError", "Заполните название компании");
            ch=false;
        }
        if(userForm.getAddress().equals("")) {
            model.addAttribute("addressError", "Заполните адрес компании");
            ch=false;
        }
        if(userForm.getName().equals("")) {
            model.addAttribute("nameError", "Заполните имя");
            ch=false;
        }

        if(userForm.getSurname().equals("")) {
            model.addAttribute("surnameError", "Заполните фамилию");
            ch=false;
        }
        if(userForm.getMobphone().equals("")) {
            model.addAttribute("mobphoneError", "Заполните мобильный телефон");
            ch=false;
        }

        if(userForm.getGender()==null) {
            model.addAttribute("genderError", "Заполните пол");
            ch=false;
        }

        if (userForm.getUsername().equals("")){
            model.addAttribute("usernameError", "Заполните email");
            ch=false;
        }
        if(!ch)
            return "/index";

        if (!userService.savePartner(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            ch=false;
        }
        if(ch)
            return "redirect:/";
        else
            return "/index";
    }

    @PostMapping("/client_registration")
    public String addClient(@ModelAttribute("clientForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        boolean ch = true;
        if (bindingResult.hasErrors()) {
            return "index";
        }
        if(userForm.getName().equals("")) {
            model.addAttribute("nameError", "Заполните имя");
            ch=false;
        }

        if(userForm.getSurname().equals("")) {
            model.addAttribute("surnameError", "Заполните фамилию");
            ch=false;
        }
        if(userForm.getMobphone().equals("")) {
            model.addAttribute("mobphoneError", "Заполните мобильный телефон");
            ch=false;
        }

        if(userForm.getGender()==null) {
            model.addAttribute("genderError", "Заполните пол");
            ch=false;
        }

        if (userForm.getUsername().equals("")){
            model.addAttribute("usernameError", "Заполните email");
            ch=false;
        }
        if(!ch)
            return "/index";

        if (!userService.saveClient(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            ch=false;
        }
        if(ch)
            return "redirect:/";
        else
            return "/index";
    }
}
