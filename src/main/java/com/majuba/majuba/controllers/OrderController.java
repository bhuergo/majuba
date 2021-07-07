package com.majuba.majuba.controllers;


import com.majuba.majuba.entities.FoodDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {



    @PostMapping("/order")
    public ModelAndView order(@RequestBody List<FoodDTO> fooddto){
        ModelAndView mav = new ModelAndView("cart-food");
        mav.addObject("cart",fooddto);
        return mav;




    }




}
