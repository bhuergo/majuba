
package com.majuba.majuba.repositories;

import com.majuba.majuba.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

    @Query("SELECT c FROM Cart c WHERE c.order.order_id = :order_id")
    public List<Cart> findByOrder (@Param("order_id") Long order_id);
}
