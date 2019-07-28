package com.dkm.supermarket.controller;


import com.dkm.supermarket.bean.Good;
import com.dkm.supermarket.params.StockInfoJson;
import com.dkm.supermarket.params.StockInfoList;
import com.dkm.supermarket.service.ShowService;
import com.dkm.supermarket.service.UpdateService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("purchaser")
@RequiresRoles(value = {"3"})
public class PurchaserController {

    @Autowired
    private ShowService showService;

    @Autowired
    private UpdateService updateService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "Purchaser/purchaserIndex";
    }

    @RequestMapping(value = "/printList", method = RequestMethod.GET)
    public ModelAndView printList(){
        ModelAndView mv = new ModelAndView();
        List<Good> goods = showService.showPurGood();
        mv.addObject("goodList",goods);
        mv.setViewName("/Purchaser/showPurGood");
        return mv;
    }

    @RequestMapping(value = "/purchaserGood", method = RequestMethod.GET)
    public String purchaserGood(){return "/Purchaser/purchaserGood";}

    @ResponseBody
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public StockInfoJson show(@RequestParam("goodID") Long goodID){

        StockInfoJson stockInfoJson = showService.showTheStock(goodID);

        return stockInfoJson;
    }

    @ResponseBody
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute StockInfoList stockInfoList, @SessionAttribute("empID") Long empID, Model model){
        ModelAndView mv = new ModelAndView();
        List<Good> goods = showService.showGoodList(stockInfoList.getGoodID());
        List<Integer> amounts = showService.showAmountList(stockInfoList.getGoodAmount());
        List<Integer> prices = showService.showPriceList(stockInfoList.getGoodPrice());
        int sum = showService.countStockSum(amounts, prices);
        updateService.updateGoodinvenAndStock(goods, amounts, prices, empID);
        mv.addObject("goodList",goods);
        mv.addObject("amountList",amounts);
        mv.addObject("priceList",prices);
        mv.addObject("sum",sum);
        mv.setViewName("/Purchaser/showList");
        return mv;
    }

}
