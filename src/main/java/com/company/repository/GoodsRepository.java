package com.company.repository;

import com.company.entity.Goods;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


public interface GoodsRepository extends CrudRepository<Goods, Long> {


    Goods findGoodsById(Long id);
    Goods findGoodsByName(String name);


    @Query("select g from GOODS g order by g.priority desc")
    List<Goods> orderGoodsByPriorityDesc();

    @Query("select g from GOODS g order by g.priority asc ")
    List<Goods> orderGoodsByPriorityAsc();


    @Query("select distinct g from GOODS g inner join WAREHOUSE1 w1 on g.id = w1.goodId.id where w1.goodCount > 0 order by g.id asc")
    List<Goods> findGoodsInWarehouse1();

    @Query("select distinct g from GOODS g inner join WAREHOUSE2 w2 on g.id = w2.goodId.id where w2.goodCount > 0 order by g.id asc")
    List<Goods> findGoodsInWarehouse2();

    @Query("select distinct g from GOODS g left join WAREHOUSE1 w1 on g.id = w1.goodId.id left join WAREHOUSE2 w2 on g.id = w2.goodId.id where w1.goodCount > 0 or w2.goodCount > 0")
    List<Goods> findGoodsInWarehouses();

    @Query("select distinct g from GOODS g inner join SALES s on g.id = s.goodId.id where s.goodCount > 0 order by g.id asc")
    List<Goods> findGoodsInSales();

    @Query("select distinct g from GOODS g left join WAREHOUSE1 w1 on g.id = w1.goodId.id left join WAREHOUSE2 w2 on g.id = w2.goodId.id left join SALES s1 on g.id = s1.goodId.id where w1.goodCount > 0 or w2.goodCount > 0")
    List<Goods> findGoodsEverywhere();
}
