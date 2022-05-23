package com.company.controllers;

import com.company.entity.Goods;
import com.company.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    private GoodsRepository goodsRepository;

    @Autowired
    public void setGoodsRepository(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
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
        return "goods/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("goods") Goods goods) {
        goodsRepository.save(goods);
        return "redirect:/goods";
    }
}
