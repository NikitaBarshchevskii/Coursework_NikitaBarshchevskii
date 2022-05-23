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

}
