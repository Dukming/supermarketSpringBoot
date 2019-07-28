package com.dkm.supermarket.bean;

import java.io.Serializable;

public class Firm implements Serializable {

    private Long id;		//厂商编号
    private String name;	//厂商名称
    private String addr;	//厂商地址
    private String tele;	//厂商电话


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getTele() {
        return tele;
    }
    public void setTele(String tele) {
        this.tele = tele;
    }

}
