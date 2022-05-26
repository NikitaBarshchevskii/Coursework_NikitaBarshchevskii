package com.company.controllers;

import com.company.entity.Goods;
import com.company.entity.Sales;
import com.company.repository.GoodsRepository;
import com.company.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sales")
public class SalesController {
    private SalesRepository salesRepository;
    private GoodsRepository goodsRepository;
    @Autowired
    public void setSalesRepository(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Autowired
    public void setGoodsRepository(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @GetMapping()
    public String printSales(Model model) {
        List<Sales> sales = (List<Sales>) salesRepository.findAll();
        model.addAttribute("sales", sales.isEmpty() ? null : sales);
        model.addAttribute("highestCount", salesRepository.orderSalesByNameDesc());
        model.addAttribute("lowestCount", salesRepository.orderSalesByNameAsc());
        return "sales/sales";
    }
    @GetMapping("/new")
    public String addSales(Model model) {
        model.addAttribute("sales", new Sales());
        model.addAttribute("goods", goodsRepository.findAll());
        return "sales/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("sales") Sales sales,
            @ModelAttribute("goods") String goodsName) {

        sales.setGoodId(goodsRepository.findGoodsByName(goodsName));
        salesRepository.save(sales);
        return "redirect:/sales";
    }

    @GetMapping("/{id}")
    public String index(@PathVariable("id") Long id,
                        Model model){
        model.addAttribute("sales", salesRepository.findSalesById(id));
        return "sales/id";
    }
    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id") Long id,
                       Model model) {
        return "sales/edit";
    }

    @PostMapping("{id}/edit")
    public String update(@ModelAttribute("goodCount") String goodCount,
                         @PathVariable("id") long id) {
        long temp = Long.parseLong(goodCount);
        Sales sale = salesRepository.findSalesById(id);
        sale.setGoodCount(temp);
        salesRepository.save(sale);
        return "redirect:/sales";
    }
    @PostMapping( "{id}/delete")
    public String delete(@PathVariable("id") Long id){
        salesRepository.deleteById(id);
        return "redirect:/sales";
    }
}
