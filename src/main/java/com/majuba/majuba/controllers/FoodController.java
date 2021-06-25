
package com.majuba.majuba.controllers;

import com.majuba.majuba.entities.Cart;
import com.majuba.majuba.entities.Category;
import com.majuba.majuba.services.FoodService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/foods")
public class FoodController {
    
    @Autowired
    private FoodService fService;
    
    
    @PostMapping("/save")
    public RedirectView save(@RequestParam Long food_id,@RequestParam byte[] image,@RequestParam String title,@RequestParam String description,@RequestParam Double price,@RequestParam Category category,@RequestParam List ingredients,@RequestParam Cart cart){
    fService.create(food_id, image, title, description, price, category, ingredients, cart);
    
    return new RedirectView("/system");
    }
    
    @PostMapping("/delete/{food_id}")
    public RedirectView delete (@PathVariable Long food_id){
    fService.delete(food_id);
    return new RedirectView("/system");
    }
}
