package com.dkm.supermarket.params;

public class RegisterGoodParams {
    private Long id;
    private String name;
    private Long firmID;
    private String price;
    private Integer inventoryAmount;
    private Integer warnAmount;
    private Integer purchaseAmount;
    private Integer isDiscount;
    private Integer isSell;

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

    public Long getFirmID() {
        return firmID;
    }

    public void setFirmID(Long firmID) {
        this.firmID = firmID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public Integer getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(Integer purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public Integer getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Integer isDiscount) {
        this.isDiscount = isDiscount;
    }

    public Integer getIsSell() {
        return isSell;
    }

    public void setIsSell(Integer isSell) {
        this.isSell = isSell;
    }
}
