package com.dkm.supermarket.bean;

import java.io.Serializable;

public class Good implements Serializable {
    private static final long serialVersionUID = -4902675595558301864L;
    private Long id;					//商品编号
    private Firm firm;				    //厂商类
    private String name;			    //商品名称
    private Integer price;				//当前售价
    private Integer isAllowDiscount;	//是否允许打折(1:允许,0:不允许)(2:未知状态,用于模糊查询功能)
    private Integer inventoryAmount;	//库存数量
    private Integer warnAmount;			//库存报警数量
    private Integer isWarn;				//是否报警(1:是,0:否)(2:未知状态,用于模糊查询功能)
    private Integer purchaseAmount;		//计划进货数
    private Integer isSell;				//是否允许销售(1:允许,0:不允许)(2:未知状态,用于模糊查询功能)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Firm getFirm() {
        return firm;
    }
    public void setFirm(Firm firm) {
        this.firm = firm;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getIsAllowDiscount() {
        return isAllowDiscount;
    }
    public void setIsAllowDiscount(Integer isAllowDiscount) {
        this.isAllowDiscount = isAllowDiscount;
    }
    public Integer getInventoryAmount() {
        return inventoryAmount;
    }
    public void setInventoryAmount(Integer inventoryAmount) {
        this.inventoryAmount = inventoryAmount;
    }
    public Integer getWarnAmount() {
        return warnAmount;
    }
    public void setWarnAmount(Integer warnAmount) {
        this.warnAmount = warnAmount;
    }
    public Integer getIsWarn() {
        return isWarn;
    }
    public void setIsWarn(Integer isWarn) {
        this.isWarn = isWarn;
    }
    public Integer getPurchaseAmount() {
        return purchaseAmount;
    }
    public void setPurchaseAmount(Integer purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }
    public Integer getIsSell() {
        return isSell;
    }
    public void setIsSell(Integer isSell) {
        this.isSell = isSell;
    }

}
