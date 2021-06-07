/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.majuba.controllers;

import com.egg.majuba.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Barbara
 */
@Controller
public class LoginController {
    
    @Autowired
    private LoginService loginservice;
    
    //Vista donde el usuario debe elegir si entra como admin o cliente
    @GetMapping("/")
    public ModelAndView elegir() {
        return new ModelAndView("choice");
    }
    
    //Al apretar "admin", redirecciona al formulario de login
    @GetMapping("/admin")
    public ModelAndView admin() {
        return new ModelAndView("login-emp");
    }
    
    //Al apretar "ingresar", redirecciona al menú para empleados
    @PostMapping("/sistema")
    public RedirectView sistema() {
        //service para chequear datos de acceso
        return new RedirectView("index-emp");
    }
    
    //Al apretar "cliente", redirecciona a elegir tamaño de mesa
    @GetMapping("/guest")
    public ModelAndView guest() {
        return new ModelAndView("size");
    }
    
    //Al apretar "comprobar", redirecciona a vista de disponibilidad
    @PostMapping("/disponible")
    public RedirectView disponibilidad() {
        //service para chequear y mostrar disponibilidad
        return new RedirectView("disponibilidad");
        // hacer que si pone "volver" vaya a size (/guest)
    }
    
    //Al apretar "ingresar", redirecciona al codigo de seguridad
    @PostMapping("/mesa")
    public RedirectView token() {
        //service para generar y guardar numero de mesa
        return new RedirectView("login-cl");
    }
    
    //Al ingresar el token, redirecciona al menú para clientes
    @PostMapping("/menu")
    public RedirectView menu() {
        //service que comprueba token
        return new RedirectView("index-cl");
    }
    
}
