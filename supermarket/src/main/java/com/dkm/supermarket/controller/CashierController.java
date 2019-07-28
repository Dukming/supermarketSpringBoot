package com.dkm.supermarket.controller;



import com.dkm.supermarket.bean.Employee;
import com.dkm.supermarket.bean.Good;
import com.dkm.supermarket.params.SaleInfoJson;
import com.dkm.supermarket.params.SaleInfoList;
import com.dkm.supermarket.service.ShowService;
import com.dkm.supermarket.service.UpdateService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("cashier")
@RequiresRoles(value = {"2"})
public class CashierController {

    @Autowired
    private ShowService showService;

    @Autowired
    private UpdateService updateService;

    @ResponseBody
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public SaleInfoJson show(@RequestParam("goodID") Long goodID, @RequestParam("menberID") Long menberID){
        long start = System.currentTimeMillis();
        SaleInfoJson saleInfoJson = showService.showTheGood(goodID,menberID);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        return saleInfoJson;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "Cashier/cashierIndex";
    }

    @ResponseBody
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute SaleInfoList saleInfoList, @SessionAttribute("empID") Long empID){

        ModelAndView mv = new ModelAndView();
        List<Good> goods = showService.showGoodList(saleInfoList.getGoodID());
        List<Integer> amounts = showService.showAmountList(saleInfoList.getGoodAmount());
        List<Integer> isDiss = showService.showIsDisList(saleInfoList.getIsDis());
        Long menberID = saleInfoList.getMenberID();
        int sum = showService.countSum(goods, amounts, isDiss);
        updateService.updateGoodinvenAndSaleAndDealAndMenber(goods, amounts, isDiss, empID, menberID, sum);
        mv.addObject("goodList", goods);
        mv.addObject("amountList", amounts);
        mv.addObject("isDisList", isDiss);
        mv.addObject("sum", sum);
        mv.setViewName("/Cashier/showSum");
        return mv;
    }


//    @ExceptionHandler(UpdateException.class)
//    public String HandleUpdateException(UpdateException e){
//        return "Exception/UpdateException";
//    }
}
