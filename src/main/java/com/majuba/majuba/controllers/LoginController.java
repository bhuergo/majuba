package com.majuba.majuba.controllers;

import com.majuba.majuba.entities.User;
import com.majuba.majuba.services.LoginService;
import com.majuba.majuba.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
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
    @PostMapping("/disponible")
    public RedirectView availability(@RequestParam Integer num_guests) {
        Integer num_tables = tableService.checkAvailability(num_guests);

        return new RedirectView("disponibilidad");
        // hacer que si disponibilidad es 0, la función "ingresar" se deshabilite (front)
        // hacer que si pone "volver" vaya a size (/guest)
    }

    //Al apretar "ingresar", redirecciona al codigo de acceso
    @PostMapping("/mesa")
    public RedirectView token() {

        //service para generar y guardar numero de mesa
        return new RedirectView("login-cl");
    }

    //Al ingresar el token, redirecciona al menú para clientes
    @PostMapping("/menu")
    public RedirectView menu() {
        //service que comprueba codigo de acceso
        return new RedirectView("index-cl");
    }
}
