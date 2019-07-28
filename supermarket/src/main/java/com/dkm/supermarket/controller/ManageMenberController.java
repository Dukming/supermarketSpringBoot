package com.dkm.supermarket.controller;


import com.dkm.supermarket.bean.Deal;
import com.dkm.supermarket.bean.Menber;
import com.dkm.supermarket.params.SelectGoodParams;
import com.dkm.supermarket.service.RegisterService;
import com.dkm.supermarket.service.ShowService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/menber")
@RequiresRoles(value = {"1"})
public class ManageMenberController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private ShowService showService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info(){
        return "Manager/manageMenber";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){return "Manager/registerMenber";}

    @ResponseBody
    @RequestMapping(value = "/regist",method = RequestMethod.POST )
    public String regist(@RequestParam("id") Long id){
        if(registerService.registerMenber(id)){
            return "<script>alert('Register success!!');history.go(-1);</script>";
        }else{
            return "<script>alert('Register failed!!');history.go(-1);</script>";
        }
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(){
        ModelAndView mv = new ModelAndView();
        List<Menber> menbers = showService.showAllMenber();
        mv.addObject("menberList", menbers);
        mv.setViewName("Manager/showAllMenber");
        return mv;
    }

    @RequestMapping(value = "/showMenber",method = RequestMethod.GET )
    public ModelAndView showMenber(@RequestParam("id") Long id){
        ModelAndView mv = new ModelAndView();
        List<Menber> menbers = showService.showMenberById(id);
        mv.addObject("menberList", menbers);
        mv.setViewName("Manager/showAllMenber");
        return mv;
    }

    @RequestMapping(value = "selectDeal", method = RequestMethod.GET)
    public String selectDeal(){
        return "/Manager/selectDeal";
    }

    @RequestMapping(value = "/showDeal",method = RequestMethod.GET )
    public ModelAndView showDeal(@RequestParam("id") Long id){
        ModelAndView mv = new ModelAndView();
        List<Deal> deals = showService.showDealById(id);
        mv.addObject("dealList",deals);
        mv.setViewName("Manager/showDeal");
        return mv;
    }

}
