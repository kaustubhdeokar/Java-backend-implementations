package com.example.kdeokar.springmvc.controller;

import com.example.kdeokar.springmvc.model.Todo;
import com.example.kdeokar.springmvc.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("username")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public String listTodos(String username, ModelMap model) {
        List<Todo> todos = todoService.retrieveTodos("in28Minutes");
        model.put("todos", todos);
        return "list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String addTodo(ModelMap modelMap) {
        modelMap.addAttribute("todo", new Todo());
        return "todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodoPost(Todo todo, ModelMap modelMap) {
        todoService.addTodo("in28Minutes", todo.getDesc(), new Date(), false);
        modelMap.clear();
        return "redirect:todos";
    }


    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id) {
        System.out.println("Here");
        todoService.deleteTodo(id);
        return "redirect:todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String updateTodo(@RequestParam int id, ModelMap map) {
        map.addAttribute("id",id);
        return "updatetodo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodoPost(@RequestParam int id, @RequestParam String desc) {
        todoService.updateTodo(id, desc);
        return "redirect:todos";
    }

}
