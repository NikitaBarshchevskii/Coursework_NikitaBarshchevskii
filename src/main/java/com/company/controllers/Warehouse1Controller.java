package com.company.controllers;

import com.company.entity.Goods;
import com.company.entity.Warehouse1;
import com.company.repository.Warehouse1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new")
    public String addWarehouse1(Model model) {
        model.addAttribute("warehouse1", new Warehouse1());
/*
        model.addAttribute("warehouse1", warehouse1Repository.findAll());
*/
        return "warehouse1/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("warehouse1") Warehouse1 warehouse1/*,
     *//*@ModelAttribute("warehouse1") String warehouse1*/) {
/*
        goods.set
*/
        warehouse1Repository.save(warehouse1);

        return "redirect:/warehouse1";
    }

    @GetMapping("/{id}")
    public String index(@PathVariable("id") Long id,
                        Model model){
        model.addAttribute("warehouse1", warehouse1Repository.findWarehouse1sById(id));
/*
        model.addAttribute("warehouse1", warehouse1Repository.findWarehouse1sByGoodId(id));
*/
        return "warehouse1/id";
    }
}
