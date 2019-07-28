package com.dkm.supermarket.controller;

import com.dkm.supermarket.bean.Employee;
import com.dkm.supermarket.dao.EmployeeDao;
import com.dkm.supermarket.exception.BanException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@SessionAttributes(names = {"emp"})
@SessionAttributes(value = {"empID"}, types = {Long.class})
public class LoginController {

    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping("/")
    public String index(){
        System.out.println("into index!");
        return "index";
    }

    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {
        System.out.println("into login!");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("id") String id, @RequestParam("password") String password) {
        Subject subject= SecurityUtils.getSubject();
        ModelAndView mv = new ModelAndView();
        UsernamePasswordToken token=new UsernamePasswordToken(id,password);

//        try {
//            subject.login(token);
//        }catch (IncorrectCredentialsException e) {
//            e.printStackTrace();
//            System.out.println("用户名或密码错误！");
//        } catch (LockedAccountException e) {
//            e.printStackTrace();
//            System.out.println("帐号已被锁定");
//        } catch (DisabledAccountException e) {
//            e.printStackTrace();
//            System.out.println("帐号已被禁用");
//        } catch (ExpiredCredentialsException e) {
//            e.printStackTrace();
//            System.out.println("帐号已过期");
//        } catch (UnknownAccountException e) {
//            e.printStackTrace();
//            System.out.println("用户不存在");
//        } catch (UnauthorizedException e) {
//            e.printStackTrace();
//            System.out.println("您没有得到相应的授权");
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("7");
//        }
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        Employee emp = employeeDao.getEmp(Long.parseLong(id));
        if(emp.getIsDelete()==1){
            subject.logout();
            throw new BanException();
        }else{
            mv.addObject("empID", emp.getId());
            System.out.println(emp.getId());
            switch(emp.getType()){
                case 1:
                    mv.setViewName("Manager/managerIndex");
                    return mv;
                case 2:
                    mv.setViewName("Cashier/cashierIndex");
                    return mv;
                case 3:
                    mv.setViewName("Purchaser/purchaserIndex");
                    return mv;

            }
        }
        return mv;
    }

//    @RequiresRoles(value = {"1"})
//    @RequestMapping(value = "test1", method = RequestMethod.GET)
//    public String test1(){
//        System.out.println("into test!");
//        return "/test1";
//    }

    @RequiresRoles(value = {"1"})
    @RequestMapping(value = "/login/managerIndex", method = RequestMethod.GET)
    public ModelAndView managerIndex(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Manager/managerIndex");
        return mv;
    }

}
