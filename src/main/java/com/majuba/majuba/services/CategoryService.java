
package com.majuba.majuba.services;

import com.majuba.majuba.entities.Category;
import com.majuba.majuba.repositories.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
        
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void create(Long category_id, String name, List foods) {
        Category category = new Category();
        category.setCategory_id(category_id);
        category.setFoods(foods);
        category.setName(name);
        categoryRepository.save(category);

    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
/*
   // @Transactional
   // public List<Cart> buscarPorNombre(String nombre) {
     //   return autorrepositorio.buscarPorNombre(nombre);
    //}
@Transactional
    public void modify(Integer cart_id,Cart cart){
        CartRepository.modify(cart_id,cart);
    }*/
    @Transactional
    public void delete(Long category_id) {
        categoryRepository.deleteById(category_id);
    }

    
    @Transactional(readOnly = true)
    public Category searchById(Long category_id) {
        Optional<Category> categoryOptional = categoryRepository.findById(category_id);
        return categoryOptional.orElse(null);
    }
    
    
}
