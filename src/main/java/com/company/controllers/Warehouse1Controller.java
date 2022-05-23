package com.company.controllers;

import com.company.entity.Goods;
import com.company.entity.Warehouse1;
import com.company.repository.Warehouse1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/warehouse1")
public class Warehouse1Controller {
    private Warehouse1Repository warehouse1Repository;

    @Autowired
    public void setWarehouse1Repository(Warehouse1Repository warehouse1Repository) {
        this.warehouse1Repository = warehouse1Repository;
    }

    @GetMapping()
    public String printWarehouse1(Model model) {
        List<Warehouse1> warehouse = (List<Warehouse1>) warehouse1Repository.findAll();
        model.addAttribute("warehouses1", warehouse.isEmpty() ? null : warehouse);
        return "warehouse1/warehouse1";
    }
}
