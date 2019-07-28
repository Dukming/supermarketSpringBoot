package com.dkm.supermarket.params;

public class SaleInfoList {
    private String goodID;
    private String goodAmount;
    private String isDis;
    private Long menberID;

    public String getGoodID() {
        return goodID;
    }

    public void setGoodID(String goodID) {
        this.goodID = goodID;
    }

    public String getGoodAmount() {
        return goodAmount;
    }

    public void setGoodAmount(String goodAmount) {
        this.goodAmount = goodAmount;
    }

    public String getIsDis() {
        return isDis;
    }

    public void setIsDis(String isDis) {
        this.isDis = isDis;
    }

    public Long getMenberID() {
        return menberID;
    }

    public void setMenberID(Long menberID) {
        this.menberID = menberID;
    }
}
