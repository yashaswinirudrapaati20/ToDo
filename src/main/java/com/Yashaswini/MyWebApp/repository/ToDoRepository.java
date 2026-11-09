package com.Yashaswini.MyWebApp.repository;

import com.Yashaswini.MyWebApp.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//<Bean that it is managing,Type of id field
public interface ToDoRepository extends JpaRepository<ToDo,Integer>{
    public List<ToDo> findByUsername(String username);
}
