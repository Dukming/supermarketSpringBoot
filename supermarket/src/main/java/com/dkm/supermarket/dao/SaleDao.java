package com.dkm.supermarket.dao;

import com.dkm.supermarket.bean.Sale;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDao {
    public int insertSale(Sale sale);
    public List<Sale> selectSaleByMultItem(@Param("minTime") String minTime, @Param("maxTime") String maxTime, @Param("sale") Sale sale);
}
