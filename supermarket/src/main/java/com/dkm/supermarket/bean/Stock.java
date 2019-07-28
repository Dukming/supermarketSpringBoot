package com.dkm.supermarket.bean;

import java.io.Serializable;

public class Stock implements Serializable {
    private Long id;			//入库编号
    private Good good;			//商品编号
    private Employee emp;		//员工编号
    private Integer amount;		//入库数量
    private Integer price;		//进货价
    private String date;		//入库日期

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
