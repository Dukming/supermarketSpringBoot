package com.dkm.supermarket.service;


import com.dkm.supermarket.bean.Employee;
import com.dkm.supermarket.bean.Good;
import com.dkm.supermarket.params.RegisterGoodParams;

import java.util.List;

public interface UpdateService {
    public boolean updateEmpPW(Long id, String password);
    public boolean updateEmp(Long id, String name, Integer type);
    public boolean updateFirm(Long id, String name, String addr, String tele);
    public boolean updateGood(RegisterGoodParams params);
    public boolean updateGoodinvenAndSaleAndDealAndMenber(List<Good> showGoodList, List<Integer> showAmountList, List<Integer> showIsDisList, Long empID, Long menberID, int sum);
    public boolean updateGoodinvenAndStock(List<Good> goodList, List<Integer> amountList, List<Integer> priceList, Long empID);
}
