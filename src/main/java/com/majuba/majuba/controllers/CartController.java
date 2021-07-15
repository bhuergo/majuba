package com.majuba.majuba.controllers;

import com.majuba.majuba.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;


}
