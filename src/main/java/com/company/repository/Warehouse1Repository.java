package com.company.repository;

import com.company.entity.Goods;
import com.company.entity.Warehouse1;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

public interface Warehouse1Repository extends CrudRepository<Warehouse1, Long> {
    Warehouse1 findWarehouse1sById(Long id);
    List<Warehouse1> findWarehouse1sByGoodId(Goods goods);
    @Transactional
    @Modifying
    @Query("delete from WAREHOUSE1 w1 where w1.goodCount = 0")
    void cleanWarehouse1();


}
