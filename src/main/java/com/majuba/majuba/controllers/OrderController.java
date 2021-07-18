package com.majuba.majuba.controllers;


import com.majuba.majuba.entities.Order;
import com.majuba.majuba.services.CartService;
import com.majuba.majuba.services.OrderService;
import com.majuba.majuba.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    CartService cartService;
    @Autowired
    TableService tableService;

    @GetMapping("/checkout/{table_id}")
    public ModelAndView showFinal(@PathVariable Long table_id) {
        ModelAndView mav = new ModelAndView("pay");
        Order order = orderService.showOrder(table_id);
        mav.addObject("order", order);
        mav.addObject("order_elements", cartService.findByOrder(order.getOrder_id()));
        return mav;
    }

    @PostMapping("/payment/{order_id}")
    public RedirectView checkout(@PathVariable Long order_id, @RequestParam String clientName, @RequestParam String email) {
        orderService.setEmail(order_id, clientName, email);
        Order order = orderService.findOrderById(order_id);
        tableService.resetTable(order.getTable().getTable_id());
        return new RedirectView("/logout");
    }

}
