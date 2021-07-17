package com.majuba.majuba.controllers;


import com.majuba.majuba.entities.FoodDTO;
import com.majuba.majuba.entities.Order;
import com.majuba.majuba.services.CartService;
import com.majuba.majuba.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    CartService cartService;

    @GetMapping
    public ModelAndView showFinal(Long table_id) {
        ModelAndView mav = new ModelAndView("pay");
        Order order = orderService.showOrder(table_id);
        mav.addObject("order", order);
        mav.addObject("order_elements", cartService.findByOrder(order.getOrder_id()));
        return mav;
    }

}
