
package com.majuba.majuba.repositories;

import com.majuba.majuba.entities.Food;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long>{
    
    @Modifying
    @Query("SELECT a FROM Food a WHERE a.title LIKE :title")
    List<Food> searchForName (@Param("title") String title);


}
