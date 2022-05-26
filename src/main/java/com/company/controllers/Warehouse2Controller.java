package com.company.controllers;

import com.company.entity.Goods;
import com.company.entity.Warehouse1;
import com.company.entity.Warehouse2;
import com.company.repository.GoodsRepository;
import com.company.repository.Warehouse1Repository;
import com.company.repository.Warehouse2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/warehouse2")
public class Warehouse2Controller {

    private Warehouse2Repository warehouse2Repository;
    private GoodsRepository goodsRepository;
    @Autowired
    public void Warehouse2Repository(Warehouse2Repository warehouse2Repository){
        this.warehouse2Repository = warehouse2Repository;
    }
    @Autowired
    public void setWarehouse2Repository(Warehouse2Repository warehouse2Repository) {
        this.warehouse2Repository = warehouse2Repository;
    }
    @Autowired
    public void setGoodsRepository(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }
    @GetMapping()
    public String printWarehouse2(Model model) {
        List<Warehouse2> warehouse2 = (List<Warehouse2>) warehouse2Repository.findAll();
        model.addAttribute("warehouses2", warehouse2.isEmpty() ? null : warehouse2);
        warehouse2Repository.cleanWarehouse2();
        return "warehouse2/warehouse2";
    }

    @GetMapping("/new")
    public String addWarehouse2(Model model) {
        model.addAttribute("warehouse2", new Warehouse2());
/*
        model.addAttribute("warehouse1", warehouse1Repository.findAll());
*/
        model.addAttribute("goods", goodsRepository.findAll());
        warehouse2Repository.cleanWarehouse2();
        return "warehouse2/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("warehouse2") Warehouse2 warehouse2,
                         @ModelAttribute("goods") String goodsName/*,
     *//*@ModelAttribute("warehouse1") String warehouse1*/) {
/*
        goods.set
*/
        warehouse2.setGoodId(goodsRepository.findGoodsByName(goodsName));
        warehouse2Repository.save(warehouse2);
        return "redirect:/warehouse2";
    }

    @GetMapping("/{id}")
    public String index(@PathVariable("id") Long id,
                        Model model){
        model.addAttribute("warehouse2", warehouse2Repository.findWarehouse2sById(id));
/*
        model.addAttribute("warehouse1", warehouse1Repository.findWarehouse1sByGoodId(id));
*/
        return "warehouse2/id";
    }
    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id") Long id,
                       Model model) {
        model.addAttribute("warehouse2", warehouse2Repository.findWarehouse2sById(id));
        return "warehouse2/edit";
    }

    @PostMapping("{id}/edit")
    public String update(@ModelAttribute("goodCount") String goodCount,
                         @PathVariable("id") long id) {
        long temp = Long.parseLong(goodCount);
        Warehouse2 warehouse2 = warehouse2Repository.findWarehouse2sById(id);
        warehouse2.setGoodCount(temp);
        warehouse2Repository.save(warehouse2);
        warehouse2Repository.cleanWarehouse2();
        return "redirect:/warehouse2";
    }
    @PostMapping( "{id}/delete")
    public String delete(@PathVariable("id") Long id){
        warehouse2Repository.deleteById(id);
        return "redirect:/warehouse2";
    }
}
