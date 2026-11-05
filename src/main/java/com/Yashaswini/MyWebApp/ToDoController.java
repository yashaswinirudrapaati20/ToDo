package com.Yashaswini.MyWebApp;

import jakarta.validation.Valid;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//@Controller

public class ToDoController {
    private ToDoService todoServ;

    public ToDoController(ToDoService todoServ) {
        this.todoServ = todoServ;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model){
        String username=getLoggedInUserName();
        model.addAttribute("todos",todoServ.findbyUsername(username));
        return "ListTodos";
    }
    @RequestMapping(value="add-todo",method= RequestMethod.GET)
    public String showNewTodoPage(ModelMap model){
        String user=getLoggedInUserName();
        ToDo todo=new ToDo(0,user,"",LocalDate.now().plusYears(1),false);
        model.put("todo",todo);
        return "todo";
    }

    @RequestMapping(value="add-todo",method= RequestMethod.POST)
    public String addNewTodoPage(ModelMap model, @Valid @ModelAttribute("todo") ToDo todo, BindingResult res){
        if(res.hasErrors()){
            return "todo";
        }
        String user=getLoggedInUserName();
        todoServ.addToDo(user,todo.getDescription(),todo.getTargetDate(),false);
        return "redirect:list-todos";
    }
    @RequestMapping(value="delete-todo")
    public String deleteTodoPage(@RequestParam int id){
        todoServ.deletebyId(id);
        return "redirect:list-todos";
    }
    @RequestMapping(value="update-todo",method=RequestMethod.GET)
    public String showUpdateToDoPage(ModelMap model,@RequestParam int id){
        ToDo todo=todoServ.findbyId(id);
        if(todo==null){
            return "redirect:list-todos";
        }
        model.addAttribute("todo",todo);
        return "todo";
    }
    @RequestMapping(value="update-todo",method=RequestMethod.POST)
    public String updatedToDoPage(@Valid @ModelAttribute("todo") ToDo todo,BindingResult res) {
        if(res.hasErrors()){
            return "todo";
        }
        todoServ.updateToDo(todo.getId(), todo.getDescription(),todo.getTargetDate(),todo.isStatus());
        return "redirect:list-todos";
    }
    public String getLoggedInUserName(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
