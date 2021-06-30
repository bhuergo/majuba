package com.majuba.majuba.controllers;


import com.majuba.majuba.entities.Table;
import com.majuba.majuba.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TableController {


    @Autowired
    private TableService tservice;


   /* @GetMapping("/todos")
    public ModelAndView InfoTables (){

        ModelAndView mav = new ModelAndView("index-emp");
        List<Table> tables = tservice.findAll();
        System.out.println(tables);
        mav.addObject("tables",tables);
        return mav;
    }*/

}
