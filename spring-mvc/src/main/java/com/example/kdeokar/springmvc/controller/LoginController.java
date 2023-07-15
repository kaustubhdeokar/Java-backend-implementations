package com.example.kdeokar.springmvc.controller;

import com.example.kdeokar.springmvc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("username")
public class LoginController {

    @Autowired
    LoginService validationService;

    //when no response body tag is present, it's a view.
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String sayHello() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String sayHello2(@RequestParam String username, @RequestParam String password, ModelMap model) {


        if (validationService.isValidUser(username, password)) {
            model.put("username", username);
            return "welcome";
        } else {
            model.put("invalidcred", "Sikeeeeeee");
            return "error";
        }


    }


}
