package com.dkm.supermarket.dao;

import com.dkm.supermarket.bean.Menber;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenberDao {
    public int insertMenber(Menber menber);
    public Menber getMenber(Long id);
    public List<Menber> getAllMenber();
    public int updateMenber(Menber menber);
}
