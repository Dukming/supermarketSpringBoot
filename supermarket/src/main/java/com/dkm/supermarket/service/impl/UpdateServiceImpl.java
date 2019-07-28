package com.dkm.supermarket.service.impl;


import com.dkm.supermarket.bean.*;
import com.dkm.supermarket.dao.*;
import com.dkm.supermarket.exception.UpdateException;
import com.dkm.supermarket.params.RegisterGoodParams;
import com.dkm.supermarket.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private FirmDao firmDao;

    @Autowired
    private GoodDao goodDao;

    @Autowired
    private SaleDao saleDao;

    @Autowired
    private StockDao stockDao;

    @Autowired
    private MenberDao menberDao;

    @Autowired
    private DealDao dealDao;


    @Override
    public boolean updateEmpPW(Long id, String password) {
        Employee emp = employeeDao.getEmp(id);
        emp.setPassword(password);
        String salt = emp.getSalt();
        String newPassword = salt+password;
        if(employeeDao.updateEmpPW(emp, newPassword)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEmp(Long id, String name, Integer type) {
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        emp.setType(type);
        if(employeeDao.updateEmp(emp) > 0 ){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean updateFirm(Long id, String name, String addr, String tele) {
        Firm firm = new Firm();
        firm.setId(id);
        firm.setName(name);
        firm.setAddr(addr);
        firm.setTele(tele);
        if(firmDao.updateFirm(firm) > 0 ){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateGood(RegisterGoodParams params) {
        Good good = new Good();
        Firm firm = new Firm();
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
        if(goodDao.updateGood(good) > 0 ){
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED)
    public boolean updateGoodinvenAndSaleAndDealAndMenber(List<Good> showGoodList, List<Integer> showAmountList,
                                                 List<Integer> showIsDisList, Long empID, Long menberID, int sum) {
        Good good ;
        int amount;
        int isDis;
        Sale sale = new Sale();
        Employee emp = new Employee();
        emp.setId(empID);
        for(int i = 0; i < showGoodList.size(); i++){
            good = showGoodList.get(i);
            amount = showAmountList.get(i);
            isDis = showIsDisList.get(i);

            try{
                goodDao.updateGoodInventoryAmountByIdOut(good.getId(),amount);
            }catch(Exception e){
                throw new UpdateException();
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sale.setGood(good);
            sale.setEmp(emp);
            sale.setAmount(amount);
            sale.setUnitPrice(good.getPrice());
            sale.setIsDiscount(isDis);
            sale.setDate(df.format(new Date()));
            if(saleDao.insertSale(sale) < 0 ){return false;}
        }
        Menber menber = menberDao.getMenber(menberID);
        if(menber==null){return true;}
        int SUM = menber.getSumOfMoney() + sum;
        int score = menber.getCurrentScore() + (SUM/10);
        menber.setSumOfMoney(SUM);
        menber.setCurrentScore(score);
        if(menberDao.updateMenber(menber) < 0){return false;}
        Deal deal = new Deal();
        deal.setMenber(menber);
        deal.setMoney(SUM);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        deal.setDate(df.format(new Date()));
        if(dealDao.insertDeal(deal) < 0 ){return false;}
        return true;
    }

    @Override
    public boolean updateGoodinvenAndStock(List<Good> goodList, List<Integer> amountList, List<Integer> priceList, Long empID) {
        Good good ;
        int amount ;
        int price ;
        Stock stock = new Stock();
        Employee emp = new Employee();
        emp.setId(empID);
        for(int i = 0; i < goodList.size(); i++){
            good = goodList.get(i);
            amount = amountList.get(i);
            price = priceList.get(i);

            goodDao.updateGoodInventoryAmountByIdIn(good.getId(),amount);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            stock.setEmp(emp);
            stock.setGood(good);
            stock.setAmount(amount);
            stock.setPrice(price);
            stock.setDate(df.format(new Date()));
            if(stockDao.insertStock(stock) < 0 ){return false;}
        }
        return true;
    }


}
