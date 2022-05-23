package com.company.controllers;

import com.company.entity.Goods;
import com.company.entity.Sales;
import com.company.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sales")
public class SalesController {
    private SalesRepository salesRepository;

    @Autowired
    public void setSalesRepository(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @GetMapping()
    public String printSales(Model model) {
        List<Sales> sales = (List<Sales>) salesRepository.findAll();
        model.addAttribute("sales", sales.isEmpty() ? null : sales);
        return "sales/sales";
    }
    @GetMapping("/new")
    public String addSales(Model model) {
        model.addAttribute("sales", new Sales());
        return "sales/new";
    }

}
