package com.company.repository;

import com.company.entity.Goods;
import com.company.entity.Warehouse1;
import org.springframework.data.repository.CrudRepository;

public interface Warehouse1Repository extends CrudRepository<Warehouse1, Long> {
    Warehouse1 findWarehouse1sById(Long id);
    Warehouse1 findWarehouse1sByGoodId(Long id);
}
