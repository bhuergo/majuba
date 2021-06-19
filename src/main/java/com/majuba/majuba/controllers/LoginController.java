package com.majuba.majuba.controllers;

import com.majuba.majuba.entities.Table;
import com.majuba.majuba.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller

public class LoginController {

    @Autowired
    private TableService tableService;

    //Vista donde el usuario debe elegir si entra como admin o cliente
    @GetMapping("/")
    public ModelAndView choose() {
        return new ModelAndView("choice");
    }

    //Al apretar "admin", redirecciona al formulario de login
    @GetMapping("/admin")
    public ModelAndView admin() {
        return new ModelAndView("login-emp");
    }

    //Al apretar "ingresar", redirecciona al menú para empleados
    @PostMapping("/system")
    public RedirectView system() {
        return new RedirectView("index-emp");
    }

    //Al apretar "cliente", redirecciona a elegir tamaño de mesa
    @GetMapping("/guest")
    public ModelAndView guest() {
        return new ModelAndView("size");
    }

    //Al apretar "comprobar", redirecciona a vista de disponibilidad
    @GetMapping("/disponible")
    public ModelAndView availability(@RequestParam Integer num_guests, HttpSession session) {
        ModelAndView mav = new ModelAndView("disponibilidad");
        mav.addObject("num_tables", tableService.checkAvailability(num_guests));
        session.setAttribute("num_guests", num_guests);
        return mav;
    }

    //Al apretar "ingresar", redirecciona al codigo de acceso
    @GetMapping("/mesa")
    public ModelAndView token(HttpSession session) {
        //asignamos una mesa y le generamos el codigo de acceso
        Integer num_guests = (Integer) session.getAttribute("num_guests");
        ModelAndView mav = new ModelAndView("login-cl");
        mav.addObject("assigned_table", tableService.generateAccessCode(num_guests).getTable_id());
        session.setAttribute("assigned_table", tableService.generateAccessCode(num_guests));
        return mav;
    }

    //Al ingresar el token, redirecciona al menú para clientes
    @PostMapping("/ingreso")
    public RedirectView menu(@RequestParam Long token, HttpSession session) {
        Table assigned_table = (Table) session.getAttribute("assigned_table");
        //service que comprueba codigo de acceso
        Boolean access = tableService.checkAccessCode(token, assigned_table.getAccess_code());
        if (access) {
            return new RedirectView("/menu");
        } else {
            return new RedirectView("/guest");
        }
    }

    @GetMapping("/menu")
    public ModelAndView menuMesa(HttpSession session) {
        return new ModelAndView("index-cl");
    }
}
