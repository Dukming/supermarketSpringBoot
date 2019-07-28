package com.dkm.supermarket.bean;

import java.io.Serializable;

public class Deal implements Serializable {
    private Long id;			//交易编号
    private Menber menber;	//会员类
    private Integer money;		//交易金额
    private String date;	//交易日期

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Menber getMenber() {
        return menber;
    }

    public void setMenber(Menber menber) {
        this.menber = menber;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
