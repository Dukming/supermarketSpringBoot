package com.dkm.supermarket.service.impl;


import com.dkm.supermarket.bean.*;
import com.dkm.supermarket.dao.*;
import com.dkm.supermarket.params.SaleInfoJson;
import com.dkm.supermarket.params.SelectGoodParams;
import com.dkm.supermarket.params.SelectSaleOrStockParams;
import com.dkm.supermarket.params.StockInfoJson;
import com.dkm.supermarket.service.ShowService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {
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
    public List<Employee> showAllEmpData() {
        return employeeDao.selectAllEmp();
    }

    @Override
    public Employee showEmpById(Long id) {
        return employeeDao.getEmp(id);
    }

    @Override
    public List<Firm> showAllFirm() {
        return firmDao.selectAllFirm();
    }

    @Override
    public List<Firm> showFirmByName(String name) {
        return firmDao.findFirmsByName(name);

    }

    @Override
    public Firm showFirmById(Long id) {
        return firmDao.getFirm(id);
    }

    @Override
    public List<Good> showAllGood() {
        return goodDao.selectAllGood();
    }

    @Override
    public Good showGoodById(Long id) {
        return goodDao.getGood(id);
    }

    @Override
    public List<Good> showGoodByMultItem(SelectGoodParams params) {
        Good good = new Good();
        Firm firm = new Firm();
        firm.setName(params.getFirmName());
        good.setName(params.getName());
        good.setFirm(firm);
        good.setIsWarn(params.getIsWarn());
        good.setIsSell(params.getIsSell());
        good.setIsAllowDiscount(params.getIsDiscount());
        return goodDao.selectGoodByMultItem(good,params.getIsOrder());
    }

    @Override
    public List<Sale> showAllSale(SelectSaleOrStockParams params) {
        String minTime = params.getMinTime();
        String maxTime = params.getMaxTime();
        if("".equals(minTime)){
            minTime = "0000-00-00";
        }
        if("".equals(maxTime)){
            maxTime = "0000-00-00";
        }
        if(minTime.equals(maxTime)){
            minTime = minTime + " 00:00:00";
            maxTime = maxTime + " 23:59:59";
        }
        Good good = new Good();
        Employee emp = new Employee();
        good.setName(params.getGoodName());
        emp.setName(params.getEmpName());
        Sale sale = new Sale();
        sale.setGood(good);
        sale.setEmp(emp);

        return saleDao.selectSaleByMultItem(minTime,maxTime,sale);
    }

    @Override
    public List<Stock> showAllStock(SelectSaleOrStockParams params) {
        String minTime = params.getMinTime();
        String maxTime = params.getMaxTime();
        if("".equals(minTime)){
            minTime = "0000-00-00";
        }
        if("".equals(maxTime)){
            maxTime = "0000-00-00";
        }
        if(minTime.equals(maxTime)){
            minTime = minTime + " 00:00:00";
            maxTime = maxTime + " 23:59:59";
        }
        Good good = new Good();
        Employee emp = new Employee();
        good.setName(params.getGoodName());
        emp.setName(params.getEmpName());
        Stock stock = new Stock();
        stock.setGood(good);
        stock.setEmp(emp);

        return stockDao.selectStockByMultItem(minTime, maxTime, stock);
    }

    @Override
    @Cacheable(value = "redisCacheManager", key = "'redis_good_'+#goodID")
    public SaleInfoJson showTheGood(Long goodID, Long menberID) {
        Good good = goodDao.getGood(goodID);
        boolean isMenber = false;
        if(menberDao.getMenber(menberID)!=null){
            isMenber = true;
        }
        SaleInfoJson saleInfoJson = new SaleInfoJson();
        saleInfoJson.setId(good.getId());
        saleInfoJson.setName(good.getName());
        saleInfoJson.setPrice(good.getPrice());
        saleInfoJson.setIsAllowDiscount(good.getIsAllowDiscount());
        saleInfoJson.setIsMenber(isMenber);
        return saleInfoJson;
    }

    @Override
    public List<Good> showGoodList(String goodIDList) {
        String[] goodsID = goodIDList.split(",");
        List<Good> goods = new ArrayList<>();
        for (int i = 0; i < goodsID.length; i++){
            Long id = Long.parseLong(goodsID[i]);
            Good good = goodDao.getGood(id);
            goods.add(good);
        }
        return goods;
    }

    @Override
    public List<Integer> showAmountList(String goodAmountList) {
        String[] amounts = goodAmountList.split(",");
        List<Integer> amountList = new ArrayList<>();

        for (int i = 0; i < amounts.length; i++){

            amountList.add(Integer.parseInt(amounts[i]));
        }
        return amountList;
    }

    @Override
    public List<Integer> showIsDisList(String IsDisList) {
        String[] isDiss = IsDisList.split(",");
        List<Integer> isDisList = new ArrayList<>();
        int isDis = 0;
        for (int i = 0; i < isDiss.length; i++){
            isDis = ("是".equals(isDiss[i])) ? 1 : 0 ;
            isDisList.add(isDis);
        }
        return isDisList;
    }

    @Override
    public List<Integer> showPriceList(String goodPrice) {
        String[] prices = goodPrice.split(",");
        List<Integer> priceList = new ArrayList<>();

        for(int i = 0; i < prices.length; i++){

            priceList.add(Integer.parseInt(prices[i]));
        }
        return priceList;
    }

    @Override
    public int countSum(List<Good> showGoodList, List<Integer> showAmountList, List<Integer> showIsDisList) {
        int price ;
        int sum = 0;
        Good good ;
        for(int i = 0; i < showGoodList.size(); i++){
            good = showGoodList.get(i);
            price = good.getPrice();
            if(showIsDisList.get(i)==1){
                price = (int)(price * 0.95);
                System.out.println(price);
            }
            sum += price * showAmountList.get(i);
        }
        return sum;
    }

    @Override
    public List<Good> showPurGood() {
        List<Good> goods = goodDao.selectAllGood();
        List<Good> toPurGoods = new ArrayList<>();
        for(Good good : goods){
            if( (good.getIsWarn() == 1) && (good.getIsSell() == 1) ){
                toPurGoods.add(good);
            }
        }
        return toPurGoods;
    }

    @Override
    public StockInfoJson showTheStock(Long goodID) {
        Good good = goodDao.getGood(goodID);
        StockInfoJson stockInfoJson = new StockInfoJson();
        stockInfoJson.setId(good.getId());
        stockInfoJson.setName(good.getName());
        return stockInfoJson;
    }

    @Override
    public int countStockSum(List<Integer> amounts, List<Integer> prices) {
        int sum = 0;
        for(int i = 0; i < amounts.size(); i++){
            sum += amounts.get(i) * prices.get(i);
        }
        return sum;
    }

    @Override
    public List<Menber> showAllMenber() {
        return menberDao.getAllMenber();
    }

    @Override
    public List<Menber> showMenberById(Long id) {
        Menber menber = menberDao.getMenber(id);
        List<Menber> menbers = new ArrayList<>();
        if(menber!=null){
            menbers.add(menber);
        }
        return menbers;
    }

    @Override
    public List<Deal> showDealById(Long id) {
        return dealDao.getDealByMenberId(id);
    }
}
