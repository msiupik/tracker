package com.msiupik.tracker.controller;

import com.msiupik.tracker.model.Task;
import com.msiupik.tracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by msiupik on 13/09/16.
 */
@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home(){
        return createDefaultView();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute Task task){
        taskService.create(task);
        return createDefaultView();
    }

    @RequestMapping(value = "/activate", method = RequestMethod.GET)
    public ModelAndView activate(@RequestParam String name){
        taskService.activate(name);
        return createDefaultView();
    }


    @RequestMapping(value = "/deactivate", method = RequestMethod.GET)
    public ModelAndView deactivate(@RequestParam String name){
        taskService.deactivate(name);
        return createDefaultView();
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public ModelAndView remove(@RequestParam String name){
        taskService.remove(name);
        return createDefaultView();
    }

    private ModelAndView createDefaultView() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("newTask", new Task());
        modelAndView.addObject("tasks", taskService.list());
        return modelAndView;
    }


}
