package com.majuba.majuba.controllers;

import com.majuba.majuba.entities.FoodDTO;
import com.majuba.majuba.services.CartService;
import com.majuba.majuba.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/saveCart/{table_id}")
    @ResponseBody
    public String saveCart(@PathVariable Long table_id, @RequestBody List<FoodDTO> foodDTOs) {
        try {
            cartService.transformDTO(table_id, foodDTOs);
        } catch (Exception e) {
            return "error";
        }
        return "ok";
    }

    @GetMapping("/pedidos")
    public ModelAndView showCarts() {
        ModelAndView mav = new ModelAndView("payments");
        mav.addObject("carts", cartService.findAll());
        return mav;
    }

    @PostMapping("/hide/{cart_id}")
    public RedirectView hidePreparedCart(@PathVariable Long cart_id) {
        cartService.hideCart(cart_id);
        return new RedirectView("/pedidos");
        
    }

}
