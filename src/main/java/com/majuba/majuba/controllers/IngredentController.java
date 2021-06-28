
package com.majuba.majuba.controllers;

import com.majuba.majuba.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/ingredients")
public class IngredentController {
    
    @Autowired
    private IngredientService iService;
    
    @PostMapping("/save")
    public RedirectView save(@RequestParam String description){
       iService.create(description);
       return new RedirectView("/system");
    }
    
    @PostMapping("/delete/{ingredient_id}")
    public RedirectView delete(@PathVariable Long ingredient_id){
    
    iService.delete(ingredient_id);
    return new RedirectView("/system");
    }
}
