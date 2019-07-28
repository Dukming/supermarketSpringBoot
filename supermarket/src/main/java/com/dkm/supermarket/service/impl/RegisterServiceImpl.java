package com.dkm.supermarket.service.impl;



import com.dkm.supermarket.bean.Employee;
import com.dkm.supermarket.bean.Firm;
import com.dkm.supermarket.bean.Good;
import com.dkm.supermarket.bean.Menber;
import com.dkm.supermarket.dao.EmployeeDao;
import com.dkm.supermarket.dao.FirmDao;
import com.dkm.supermarket.dao.GoodDao;
import com.dkm.supermarket.dao.MenberDao;
import com.dkm.supermarket.params.RegisterGoodParams;
import com.dkm.supermarket.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private FirmDao firmDao;

    @Autowired
    private GoodDao goodDao;

    @Autowired
    private MenberDao menberDao;

    @Override
    public boolean registerEmp(String name, String pw, Integer type) {
        if(name==null||name==""){return false;}
        if(pw==null||pw==""){return false;}
        if(type==null||type==0){return false;}
        int max = 999;
        int min = 100;
        String salt = String.valueOf(new Random().nextInt(max-min)+min);
        Employee emp = new Employee();
        emp.setName(name);
        emp.setPassword(pw);
        emp.setType(type);
        emp.setSalt(salt);
        String newPassword = salt+pw;
        if(employeeDao.insertEmp(emp, newPassword) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean registerFirm(String name, String addr, String tele) {
        if(name==null||name==""){return false;}
        if(addr==null||addr==""){return false;}
        if(tele==null||tele==""){return false;}
        Firm firm = new Firm();
        firm.setName(name);
        firm.setAddr(addr);
        firm.setTele(tele);
        if(firmDao.insertFirm(firm) > 0 ){
            return true;
        }
        return false;
    }

    @Override
    public boolean registerGood(RegisterGoodParams params) {
        Good good = new Good();
        Firm firm = new Firm();
        if(params.getId()==null||params.getId()==0){return false;}
        if(params.getFirmID()==null||params.getFirmID()==0){return false;}
        if(params.getName()==null||params.getName()==""){return false;}
        if(params.getPrice()==null||params.getPrice()==""){return false;}
        if(params.getInventoryAmount()==null||params.getInventoryAmount()==0){return false;}
        if(params.getWarnAmount()==null||params.getWarnAmount()==0){return false;}
        if(params.getPurchaseAmount()==null||params.getPurchaseAmount()==0){return false;}
        if(params.getIsDiscount()==null||params.getIsDiscount()==0){return false;}
        if(params.getIsSell()==null||params.getIsSell()==0){return false;}
        Number num = (Number)(Float.parseFloat(params.getPrice())*100);
        Integer price = num.intValue();
        firm.setId(params.getFirmID());
        good.setId(params.getId());
        good.setFirm(firm);
        good.setName(params.getName());
        good.setPrice(price);
        good.setInventoryAmount(params.getInventoryAmount());
        good.setWarnAmount(params.getWarnAmount());
        good.setPurchaseAmount(params.getPurchaseAmount());
        good.setIsAllowDiscount(params.getIsDiscount());
        good.setIsSell(params.getIsSell());
        if(goodDao.insertGood(good) > 0 ){
            return true;
        }
        return false;
    }

    @Override
    public boolean registerMenber(Long id) {
        Menber menber = new Menber();
        menber.setId(id);
        menber.setCurrentScore(0);
        menber.setSumOfMoney(0);
        if(menberDao.insertMenber(menber) > 0 ){
            return true;
        }
        return false;
    }
}
