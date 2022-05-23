package com.company.controllers;

import com.company.entity.Warehouse1;
import com.company.entity.Warehouse2;
import com.company.repository.Warehouse2Repository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/warehouse2")
public class Warehouse2Controller {
    private Warehouse2Repository warehouse2Repository;

    public String printWarehouse2(Model model) {
        List<Warehouse2> warehouse = (List<Warehouse2>) warehouse2Repository.findAll();
        model.addAttribute("warehouses2", warehouse.isEmpty() ? null : warehouse);
        return "warehouse2/warehouse2";
    }
}
