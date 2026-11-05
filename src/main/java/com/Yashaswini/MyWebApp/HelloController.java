package com.Yashaswini.MyWebApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
    @RequestMapping("/sayhello")
    @ResponseBody
    public String sayHello() {
        return "Hello World!";
    }
    @RequestMapping("/sayhello1")
    @ResponseBody
    public String sayHellohtml() {
        StringBuffer sb=new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>");
        sb.append("My tirle");
        sb.append("</head>");
        sb.append("</title>");
        sb.append("<body>");
        sb.append("My first page");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
    @RequestMapping("/sayHellojsp")
    public String sayHellojsp(){
        return "sayHello";
    }

}