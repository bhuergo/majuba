
package com.majuba.majuba.repositories;

import com.majuba.majuba.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    
    @Query("SELECT o FROM Order o WHERE o.table.table_id = :table_id")
    public Order existingTable(@Param("table_id") Long table_id);
}
