package com.dkm.supermarket.controller;



import com.dkm.supermarket.bean.Sale;
import com.dkm.supermarket.params.SelectSaleOrStockParams;
import com.dkm.supermarket.service.ShowService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sale")
@RequiresRoles(value = {"1"})
public class ManageSaleController {

    @Autowired
    private ShowService showService;

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String select(){return "Manager/selectSale";}

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(SelectSaleOrStockParams params){
        ModelAndView mv = new ModelAndView();
        List<Sale> sales = showService.showAllSale(params);
        int sum = 0;
        for(Sale temp : sales){
            sum += (temp.getUnitPrice() * temp.getAmount());
            if(temp.getIsDiscount()==1){
                sum = (int) (sum * 0.95);
            }

        }
        mv.addObject("saleList", sales);
        mv.addObject("sum",sum);
        mv.setViewName("Manager/showSale");
        return mv;
    }

}
