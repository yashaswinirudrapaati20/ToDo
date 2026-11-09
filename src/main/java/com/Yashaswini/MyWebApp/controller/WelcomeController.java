package com.Yashaswini.MyWebApp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
@SessionAttributes("name")
@Controller
public class WelcomeController {
    @RequestMapping(value="/welcome", method=RequestMethod.GET)
    public String goToWelcomePage(ModelMap model) {
        model.put("name", getLoggedInUserName());
        return "welcome";
    }
    private String getLoggedInUserName(){
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
