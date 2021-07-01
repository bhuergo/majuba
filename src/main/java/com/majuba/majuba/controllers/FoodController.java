
package com.majuba.majuba.controllers;


import com.majuba.majuba.entities.Food;
import com.majuba.majuba.repositories.FoodRepository;
import com.majuba.majuba.services.CategoryService;
import com.majuba.majuba.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;


@Controller
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private CategoryService cService;

    @Autowired
    private FoodService fService;
    @Autowired
    private FoodRepository foodRepository;


    @PostMapping("/save")
    public RedirectView save(@RequestParam MultipartFile image, @RequestParam String title, @RequestParam String description, @RequestParam Double price, @RequestParam("category") Long category_id) {
        fService.create(image, title, description, price, category_id);

        return new RedirectView("/system");
    }

    @PostMapping("/delete/{food_id}")
    public RedirectView delete(@PathVariable Long food_id) {
        fService.delete(food_id);
        return new RedirectView("/system");
    }

    @GetMapping("/edit/{food_id}")
    public ModelAndView edit(@PathVariable Long food_id) {
        ModelAndView mav = new ModelAndView("edit-food");
        mav.addObject("food", fService.searchById(food_id));
        mav.addObject("categories", cService.fidAll());
        return mav;
    }

    @PostMapping("/savechanges")
    public RedirectView edit(@RequestParam Long food_id, @RequestParam byte[] image, @RequestParam String title, @RequestParam String description, @RequestParam Double price, @RequestParam("category") Long category_id) {
        fService.edit(food_id, image, title, description, price, category_id);
        return new RedirectView("/comidas");

    }

    //PARTE CLIENTE

    @GetMapping("/image/{food_id}")
    @ResponseBody
    public ResponseEntity<byte[]> showFoodImage(@PathVariable Long food_id) throws IOException {
        Food food = fService.searchById(food_id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(food.getImage());
    }

    @GetMapping("/entradas")
    public ModelAndView show() {
        ModelAndView mav = new ModelAndView("entradas");
        mav.addObject("foods", fService.findByCategory("entradas"));
        return mav;
    }
    /*




    @GetMapping("/menu/ensaladas")
    public ModelAndView show(){
        ModelAndView mav = new ModelAndView("ensaladas");
        mav.addObject("foods",fService.findByCategory(category_name));

        return mav;
    }
    @GetMapping("/menu/minutas")
    public ModelAndView show(){
        ModelAndView mav = new ModelAndView("minutas");
        mav.addObject("foods",fService.findByCategory(category_name));

        return mav;
    }
    @GetMapping("/menu/carnes")
    public ModelAndView show(){
        ModelAndView mav = new ModelAndView("carnes");
        mav.addObject("foods",fService.findByCategory(category_name));

        return mav;
    }
    @GetMapping("/menu/pescados")
    public ModelAndView show(){
        ModelAndView mav = new ModelAndView("index-cl");
        mav.addObject("foods",fService.findByCategory(category_name));

        return mav;
    }
    @GetMapping("/menu/guarniciones")
    public ModelAndView show(){
        ModelAndView mav = new ModelAndView("index-cl");
        mav.addObject("foods",fService.findByCategory(category_name));

        return mav;
    }
    @GetMapping("/menu/pastas")
    public ModelAndView show(){
        ModelAndView mav = new ModelAndView("index-cl");
        mav.addObject("foods",fService.findByCategory(category_name));

        return mav;
    }
    @GetMapping("/menu/pizzas")
    public ModelAndView show(){
        ModelAndView mav = new ModelAndView("index-cl");
        mav.addObject("foods",fService.findByCategory(category_name));

        return mav;
    }
    @GetMapping("/menu/postres")
    public ModelAndView show(){
        ModelAndView mav = new ModelAndView("index-cl");
        mav.addObject("foods",fService.findByCategory(category_name));

        return mav;
    }
    @GetMapping("/menu/bebidas")
    public ModelAndView show(){
        ModelAndView mav = new ModelAndView("index-cl");
        mav.addObject("foods",fService.findByCategory(category_name));

        return mav;
    }
    @GetMapping("/menu/cafeteria")
    public ModelAndView show(){
        ModelAndView mav = new ModelAndView("index-cl");
        mav.addObject("foods",fService.findByCategory(category_name));

        return mav;
    }

*/

}
