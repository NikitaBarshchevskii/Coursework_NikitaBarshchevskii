package com.company.repository;

import com.company.entity.Goods;
import com.company.entity.Sales;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SalesRepository extends CrudRepository<Sales, Long> {
    Sales findSalesById(Long id);
    List<Sales> findSalesByGoodId(Goods goods);

    @Query("select s from SALES s order by s.goodCount desc ")
    List<Sales> orderSalesByNameDesc();

    @Query("select s from SALES s order by s.goodCount asc ")
    List<Sales> orderSalesByNameAsc();

    @Query("select s from SALES s inner join WAREHOUSE1 w1 on s.goodId.id = w1.goodId.id where w1.goodCount > 0 order by s.id asc")
    List<Sales> getSameIdFromWarehouse1();

}
