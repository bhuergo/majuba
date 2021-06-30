
package com.majuba.majuba.controllers;

import com.majuba.majuba.services.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/waiters")
public class WaiterController {
    
    @Autowired
    private WaiterService wService;
    
    @PostMapping("/save")
    public RedirectView save(@RequestParam String name){
    wService.create(name);
    return new RedirectView("/system");
    }
    
    @PostMapping("/delete/{waiter_id}")
    public RedirectView delete(@PathVariable Long waiter_id){
    wService.delete(waiter_id);

    return new RedirectView("/system");
    }
}
