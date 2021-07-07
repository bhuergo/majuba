package com.majuba.majuba.controllers;


import com.majuba.majuba.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TableController {


    @Autowired
    private TableService tservice;



}
