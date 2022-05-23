package com.company.controllers;

import com.company.entity.Goods;
import com.company.repository.GoodsRepository;
import com.company.repository.SalesRepository;
import com.company.repository.Warehouse1Repository;
import com.company.repository.Warehouse2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    private GoodsRepository goodsRepository;
    private SalesRepository salesRepository;
    private Warehouse1Repository warehouse1Repository;
    private Warehouse2Repository warehouse2Repository;

    @Autowired
    public void setGoodsRepository(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }
    @Autowired
    public void setSalesRepository(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }
    @Autowired
    public void setWarehouse1Repository(Warehouse1Repository warehouse1Repository) {
        this.warehouse1Repository = warehouse1Repository;
    }
    @Autowired
    public void setWarehouse2Repository(Warehouse2Repository warehouse2Repository) {
        this.warehouse2Repository = warehouse2Repository;
    }

    @GetMapping()
    public String printGoods(Model model) {
        List<Goods> goods = (List<Goods>) goodsRepository.findAll();
        model.addAttribute("goods", goods.isEmpty() ? null : goods);
        return "goods/goods";
    }

    @GetMapping("/new")
    public String addGoods(Model model) {
        model.addAttribute("goods", new Goods());
/*
        model.addAttribute("warehouse1", warehouse1Repository.findAll());
*/
        return "goods/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("goods") Goods goods/*,
                         *//*@ModelAttribute("warehouse1") String warehouse1*/) {
/*
        goods.set
*/
        goodsRepository.save(goods);

        return "redirect:/goods";
    }

    @GetMapping("/{id}")
    public String index(@PathVariable("id") Long id,
                        Model model){
        model.addAttribute("good", goodsRepository.findGoodsById(id));
/*
        model.addAttribute("warehouse1", warehouse1Repository.findWarehouse1sByGoodId(id));
*/
        return "goods/id";
    }

}
