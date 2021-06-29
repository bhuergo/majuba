
package com.majuba.majuba.controllers;

import com.majuba.majuba.entities.Cart;
import com.majuba.majuba.entities.Category;
import com.majuba.majuba.services.CategoryService;
import com.majuba.majuba.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private CategoryService cService;
    
    @Autowired
    private FoodService fService;




    @PostMapping("/save")
    public RedirectView save(@RequestParam byte[] image,@RequestParam String title,@RequestParam String description,@RequestParam Double price,@RequestParam("category") Long category_id){
    fService.create(image, title, description, price, category_id);
    
    return new RedirectView("/system");
    }
    
    @PostMapping("/delete/{food_id}")
    public RedirectView delete (@PathVariable Long food_id){
    fService.delete(food_id);
    return new RedirectView("/system");
    }
    @GetMapping("/edit/{food_id}")
    public ModelAndView edit(@PathVariable Long food_id){
        ModelAndView mav = new ModelAndView("edit-food");
        mav.addObject("food", fService.searchById(food_id));
        mav.addObject("categories",cService.fidAll());
        return mav;
    }
    @PostMapping("/savechanges")
    public RedirectView edit(@RequestParam Long food_id,@RequestParam byte[] image,@RequestParam String title,@RequestParam String description,@RequestParam Double price,@RequestParam("category") Long category_id){
      fService.edit(food_id,image,title,description,price,category_id);
      return new RedirectView("/comidas");

    }

    //PARTE CLIENTE




}
