package com.dkm.supermarket.controller;


import com.dkm.supermarket.bean.Employee;
import com.dkm.supermarket.service.DeleteService;
import com.dkm.supermarket.service.RegisterService;
import com.dkm.supermarket.service.ShowService;
import com.dkm.supermarket.service.UpdateService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/emp")
@RequiresRoles(value = {"1"})
public class ManageEmpController {

    @Autowired
    private ShowService showService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DeleteService deleteService;

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info(){
        return "Manager/manageEmp";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(){
        ModelAndView mv = new ModelAndView();
        List<Employee> employees = showService.showAllEmpData();
        mv.addObject("empList",employees);
        mv.setViewName("Manager/showAllEmp");
        return mv;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "Manager/registerEmp";
    }

    @ResponseBody
    @RequestMapping(value = "/updatePW", method = RequestMethod.POST)
    public String updatePW(@RequestParam("id") Long id, @RequestParam("password") String password){
        if("".equals(password)){
            return "<script>alert('password is empty!!');history.go(-1);</script>";
        }if(updateService.updateEmpPW(id, password)){
            return "<script>alert('Modified success!!');history.go(-1);</script>";

        }else{
            return "<script>alert('Modified failed!!');history.go(-1);</script>";

        }


    }



    @RequestMapping(value = "/edit",method = RequestMethod.GET )
    public ModelAndView edit(@RequestParam("id") Long id){
        ModelAndView mv = new ModelAndView();
        Employee emp = showService.showEmpById(id);
        mv.addObject("emp",emp);
        mv.setViewName("Manager/editEmp");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/del",method = RequestMethod.GET )
    public String del(@RequestParam("id") Long id, @RequestParam("isDelete") Integer isDelete){
        if(deleteService.delEmp(id, isDelete)){
            return "<script>alert('Modified success!!');history.go(-1);</script>";
        }else{
            return "<script>alert('Modified failed!!');history.go(-1);</script>";

        }

    }

    @ResponseBody
    @RequestMapping(value = "/regist",method = RequestMethod.POST )
    public String regist(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("empType") Integer empType){
        if(registerService.registerEmp(name, password, empType)){
            return "<script>alert('Register success!!');history.go(-1);</script>";
        }else{
            return "<script>alert('Register failed!!');history.go(-1);</script>";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateEmp",method = RequestMethod.POST )
    public String updateEmp(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("type") Integer type){
        if(updateService.updateEmp(id, name, type)){
            return "<script>alert('Update success!!');window.location.href='/emp/show';</script>";
        }else{
            return "<script>alert('Update failed!!');window.location.href='/emp/show';</script>";
        }
    }

}
