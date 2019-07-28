package com.dkm.supermarket.dao;

import com.dkm.supermarket.bean.Firm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirmDao {
    public int insertFirm(Firm firm);
    public int updateFirm(Firm firm);
    public Firm getFirm(Long id);
    public List<Firm> selectAllFirm();
    public List<Firm> findFirmsByName(String firmName);
}
