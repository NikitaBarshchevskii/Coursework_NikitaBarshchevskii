package com.company.controllers;

import com.company.entity.Goods;
import com.company.entity.Sales;
import com.company.entity.Warehouse1;
import com.company.repository.GoodsRepository;
import com.company.repository.Warehouse1Repository;
import com.company.repository.Warehouse2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/warehouse1")
public class Warehouse1Controller {

    private Warehouse1Repository warehouse1Repository;
    private GoodsRepository goodsRepository;


    @Autowired
    public void Warehouse1Repository(Warehouse1Repository warehouse1Repository){
        this.warehouse1Repository = warehouse1Repository;
    }
    @Autowired
    public void setWarehouse1Repository(Warehouse1Repository warehouse1Repository) {
        this.warehouse1Repository = warehouse1Repository;
    }
    @Autowired
    public void setGoodsRepository(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }
    @GetMapping()
    public String printWarehouse1(Model model) {
        List<Warehouse1> warehouse1 = (List<Warehouse1>) warehouse1Repository.findAll();
        model.addAttribute("warehouses1", warehouse1.isEmpty() ? null : warehouse1);
        warehouse1Repository.cleanWarehouse1();
        return "warehouse1/warehouse1";
    }

    @GetMapping("/new")
    public String addWarehouse1(Model model) {
        model.addAttribute("warehouse1", new Warehouse1());
/*
        model.addAttribute("warehouse1", warehouse1Repository.findAll());
*/
        model.addAttribute("goods", goodsRepository.findAll());
        warehouse1Repository.cleanWarehouse1();
        return "warehouse1/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("warehouse1") Warehouse1 warehouse1,
                         @ModelAttribute("goods") String goodsName/*,
     *//*@ModelAttribute("warehouse1") String warehouse1*/) {
/*
        goods.set
*/
        warehouse1.setGoodId(goodsRepository.findGoodsByName(goodsName));
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
    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id") Long id,
                       Model model) {
        model.addAttribute("warehouse1", warehouse1Repository.findWarehouse1sById(id));
        return "warehouse1/edit";
    }

    @PostMapping("{id}/edit")
    public String update(@ModelAttribute("goodCount") String goodCount,
                         @PathVariable("id") long id) {
        long temp = Long.parseLong(goodCount);
        Warehouse1 warehouse1 = warehouse1Repository.findWarehouse1sById(id);
        warehouse1.setGoodCount(temp);
        warehouse1Repository.save(warehouse1);
        warehouse1Repository.cleanWarehouse1();
        return "redirect:/warehouse1";
    }
    @PostMapping( "{id}/delete")
    public String delete(@PathVariable("id") Long id){
            warehouse1Repository.deleteById(id);
            return "redirect:/warehouse1";
    }
}
