package com.Yashaswini.MyWebApp.service;

import com.Yashaswini.MyWebApp.entity.ToDo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ToDoService {
    private static List<ToDo> todos=new ArrayList<>();
    private static int todosCount=0;
    static{
        todos.add(new ToDo(++todosCount,"yashu","Learn Spring 1", LocalDate.now().plusYears(1),false));
        todos.add(new ToDo(++todosCount,"yashu","Learn Devops 1", LocalDate.now().plusYears(2),false));
        todos.add(new ToDo(++todosCount,"yashu","Learn FullStack 1", LocalDate.now().plusYears(2),false));
        todos.add(new ToDo(++todosCount,"yashu","Learn GenAI 1", LocalDate.now().plusYears(1),false));
    }
    public List<ToDo>findbyUsername(String username){
        Predicate<?super ToDo>predicate=todo->todo.getUsername().equalsIgnoreCase(username);
        return todos.stream()
                .filter(predicate)
                .toList();
    }

    public void addToDo(String username,String description,LocalDate targetDate,boolean status){
        ToDo todo=new ToDo(++todosCount,username,description,targetDate,status);
        todos.add(todo);
    }
    public void deletebyId(int id){
        //bean->bean.getId()==id
        Predicate<?super ToDo>predicate=todo->todo.getId()==id;
        todos.removeIf(predicate);
    }
    public ToDo findbyId(int id){
        Predicate<?super ToDo>predicate=todo->todo.getId()==id;
       ToDo todo=todos.stream().filter(predicate).findFirst().orElse(null);
       return todo;
    }
    public void updateToDo(int id,String description,LocalDate targetDate,boolean status){
        Predicate<?super ToDo>predicate=todo->todo.getId()==id;
        ToDo todo=todos.stream().filter(predicate).findFirst().get();
        todo.setDescription(description);
        todo.setTargetDate(targetDate);
        todo.setStatus(status);
    }
}
