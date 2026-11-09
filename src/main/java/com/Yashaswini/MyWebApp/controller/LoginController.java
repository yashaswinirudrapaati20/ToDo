
package com.Yashaswini.MyWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/")
    public String start(){
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
   /* @GetMapping("/welcome")
    public String welcomePage() {
        return "welcome";
    }*/


}
