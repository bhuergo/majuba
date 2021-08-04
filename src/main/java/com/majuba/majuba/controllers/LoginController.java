package com.majuba.majuba.controllers;

import com.majuba.majuba.entities.Food;
import com.majuba.majuba.entities.Table;
import com.majuba.majuba.entities.User;
import com.majuba.majuba.entities.Waiter;
import com.majuba.majuba.services.CategoryService;
import com.majuba.majuba.services.TableService;
import com.majuba.majuba.services.UserService;
import com.majuba.majuba.services.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

public class LoginController {


    @Autowired
    private CategoryService categoryService;


    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TableService tableService;
    @Autowired
    private UserService userService;
    @Autowired
    private WaiterService waiterService;

    //Vista donde el usuario debe elegir si entra como admin o cliente
    @GetMapping("/")
    public ModelAndView choose() {
        return new ModelAndView("choice");
    }

    //Al apretar "admin", redirecciona al formulario de login
    @GetMapping("/admin")
    public ModelAndView admin(HttpSession session) {
        ModelAndView mav = new ModelAndView("login-emp");
        mav.addObject("user", new User());
        return mav;
    }

    //Al apretar "ingresar", redirecciona al menú para empleados
    @GetMapping("/system")
    public ModelAndView system() {
        ModelAndView mav = new ModelAndView("index-emp");
        List<Table> tables = tableService.findAll();
        mav.addObject("tables", tableService.findAll());
        mav.addObject("food", new Food());
        mav.addObject("categories", categoryService.findAll());
        mav.addObject("Waiters", waiterService.fidAll());
        mav.addObject("waiter", new Waiter());
        return mav;
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
    public RedirectView menu(@RequestParam Long token, HttpSession session, HttpServletRequest request) throws ServletException {
        Table assigned_table = (Table) session.getAttribute("assigned_table");
        //service que comprueba codigo de acceso
        Boolean access = tableService.checkAccessCode(token, assigned_table.getAccess_code());
        if (access) {
            request.login("cliente", "root");
            tableService.ocupateTable(assigned_table.getTable_id());

            return new RedirectView("/menu");
        } else {
            return new RedirectView("/guest");
        }
    }

    @GetMapping("/menu")
    public ModelAndView menuMesa(HttpSession session){


        try {
            ModelAndView mav = new ModelAndView("index-cl");
            Table assigned_table = (Table) session.getAttribute("assigned_table");
            Long table_id = assigned_table.getTable_id();
            mav.addObject("table", table_id);
            return mav;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ModelAndView("choice");

        }

    }

    @GetMapping("/menu-emp")
    public ModelAndView menuEmp(HttpSession session) {
        return new ModelAndView("menu-emp");
    }

}
