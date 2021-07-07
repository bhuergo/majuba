
package com.majuba.majuba.repositories;

import com.majuba.majuba.entities.Category;
import com.majuba.majuba.entities.Food;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long>{
    

    @Query("SELECT f FROM Food f WHERE f.title LIKE :title")
    List<Food> searchForName (@Param("title") String title);


    @Modifying
    @Query("UPDATE Food f SET f.image = :image, f.title = :title, f.description = :description, f.price = :price, f.category = :category_id WHERE f.food_id = :food_id")
     void edit(@Param("food_id") Long food_id, @Param("image") byte[] image, @Param("title") String title, @Param("description") String description , @Param("price")Double price, @Param("category_id")Category category_id);

    @Query("SELECT f From Food f WHERE f.category.name = :name")
    List <Food> searchForCategory (@Param ("name") String name);




}
