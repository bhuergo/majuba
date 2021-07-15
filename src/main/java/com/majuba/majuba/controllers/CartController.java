package com.majuba.majuba.controllers;

import com.majuba.majuba.entities.FoodDTO;
import com.majuba.majuba.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/saveCart/{table_id}")
    @ResponseBody
    public RedirectView saveCart(@PathVariable Long table_id, @RequestParam(name = "foods") List<FoodDTO> foodDTOs) {

        return new RedirectView("/menu");
    }
}
