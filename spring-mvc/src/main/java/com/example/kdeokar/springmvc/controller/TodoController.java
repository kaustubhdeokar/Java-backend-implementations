package com.example.kdeokar.springmvc.controller;

import com.example.kdeokar.springmvc.model.Todo;
import com.example.kdeokar.springmvc.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public String listTodos(String user, ModelMap model) {
        List<Todo> todos = todoService.retrieveTodos("in28Minutes");
        model.put("todos", todos);
        return "todo";
    }

}
