
package com.majuba.majuba.repositories;

import com.majuba.majuba.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table, Long>{

    @Query("SELECT COUNT (t) FROM Table t WHERE t.capacity >= :num_guests AND t.available = true")
    Integer num_tables(@Param("num_guests") Integer num_guests);

    @Query(value = "SELECT t.* FROM restaurant_table t WHERE t.capacity >= :num_guests AND t.available = true ORDER BY t.capacity ASC LIMIT 1", nativeQuery = true)
    Table assigned_table(@Param("num_guests") Integer num_guests);

    @Modifying
    @Query("UPDATE Table t SET t.available = false WHERE t.table_id = :table_id")
    public void updateAvailability(@Param("table_id") Long table_id);

}
