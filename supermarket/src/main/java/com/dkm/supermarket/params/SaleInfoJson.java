package com.dkm.supermarket.params;

import java.io.Serializable;

public class SaleInfoJson implements Serializable {

    private static final long serialVersionUID = 1877132903702099138L;
    private Long id;
    private String name;
    private Integer price;
    private Integer isAllowDiscount;
    private boolean isMenber;

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

    public boolean getIsMenber() {
        return isMenber;
    }

    public void setIsMenber(boolean isMenber) {
        this.isMenber = isMenber;
    }
}
