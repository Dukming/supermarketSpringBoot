package com.dkm.supermarket.service;


import com.dkm.supermarket.params.RegisterGoodParams;

public interface RegisterService {
    public boolean registerEmp(String name, String pw, Integer type);

    public boolean registerFirm(String name, String addr, String tele);

    public boolean registerGood(RegisterGoodParams params);

    public boolean registerMenber(Long id);
}
