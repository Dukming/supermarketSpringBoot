package com.dkm.supermarket.dao;

import com.dkm.supermarket.bean.Deal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealDao {
    public int insertDeal(Deal deal);
    public List<Deal> getDealByMenberId(Long menberID);
}
