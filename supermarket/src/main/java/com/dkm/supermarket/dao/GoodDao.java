package com.dkm.supermarket.dao;

import com.dkm.supermarket.bean.Good;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodDao {
    public int insertGood(Good good);
    public int updateGood(Good good);
    public int updateGoodInventoryAmountByIdIn(@Param("id") Long id, @Param("amount") int amount);
    public int updateGoodInventoryAmountByIdOut(@Param("id") Long id, @Param("amount") int amount);
    public Good getGood(Long id);
    public List<Good> selectAllGood();
    public List<Good> selectGoodByMultItem(@Param("good") Good good, @Param("isOrder") Integer isOrderByInventoryAmount);
}
