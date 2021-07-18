
package com.majuba.majuba.repositories;

import com.majuba.majuba.entities.Table;
import com.majuba.majuba.entities.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<Table, Long>{

    @Query("SELECT COUNT (t) FROM Table t WHERE t.capacity >= :num_guests AND t.available = true")
    Integer num_tables(@Param("num_guests") Integer num_guests);

    @Query(value = "SELECT t.* FROM restaurant_table t WHERE t.capacity >= :num_guests AND t.available = true ORDER BY t.capacity ASC LIMIT 1", nativeQuery = true)
    Table assigned_table(@Param("num_guests") Integer num_guests);

    @Modifying
    @Query(value="UPDATE restaurant_table t SET t.available = not t.available WHERE t.table_id = :table_id" ,nativeQuery = true)
    public void updateAvailability(@Param("table_id") Long table_id);

    @Modifying
    @Query("UPDATE Table t SET t.waiters = :waiters WHERE t.table_id = :table_id")
    public void assignWaiters(@Param("table_id") Long table_id, @Param("waiters") List<Waiter> waiter);

    @Modifying
    @Query(value = "UPDATE restaurant_table t SET t.access_code = null WHERE t.table_id = :table_id" ,nativeQuery = true)
    public void reset(@Param("table_id") Long table_id);


}
