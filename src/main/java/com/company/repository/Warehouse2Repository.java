package com.company.repository;

import com.company.entity.Goods;
import com.company.entity.Warehouse1;
import com.company.entity.Warehouse2;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface Warehouse2Repository extends CrudRepository<Warehouse2, Long> {
    Warehouse2 findWarehouse2sById(Long id);
    List<Warehouse2> findWarehouse2sByGoodId(Goods goods);
    @Transactional
    @Modifying
    @Query("delete from WAREHOUSE2 w2 where w2.goodCount = 0")
    void cleanWarehouse2();

}
