package com.dkm.supermarket.service;

import com.dkm.supermarket.bean.*;
import com.dkm.supermarket.params.SaleInfoJson;
import com.dkm.supermarket.params.SelectGoodParams;
import com.dkm.supermarket.params.SelectSaleOrStockParams;
import com.dkm.supermarket.params.StockInfoJson;

import java.util.List;

public interface ShowService {
    public List<Employee> showAllEmpData();

    public Employee showEmpById(Long id);

    public List<Firm> showAllFirm();

    public List<Firm> showFirmByName(String name);

    public Firm showFirmById(Long id);

    public List<Good> showAllGood();

    public Good showGoodById(Long id);

    public List<Good> showGoodByMultItem(SelectGoodParams params);

    public List<Sale> showAllSale(SelectSaleOrStockParams params);

    public List<Stock> showAllStock(SelectSaleOrStockParams params);

    public SaleInfoJson showTheGood(Long goodID, Long menberID);

    public List<Good> showGoodList(String goodIDList);

    public List<Integer> showAmountList(String goodAmountList);

    public List<Integer> showIsDisList(String IsDisList);

    public List<Integer> showPriceList(String goodPrice);

    public int countSum(List<Good> showGoodList, List<Integer> showAmountList, List<Integer> showIsDisList);

    public List<Good> showPurGood();

    public StockInfoJson showTheStock(Long goodID);

    public int countStockSum(List<Integer> amounts, List<Integer> prices);

    public List<Menber> showAllMenber();

    public List<Menber> showMenberById(Long id);

    public List<Deal> showDealById(Long id);
}
