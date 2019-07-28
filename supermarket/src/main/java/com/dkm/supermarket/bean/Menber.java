package com.dkm.supermarket.bean;

public class Menber {
    private Long id;				//会员卡号
    private Integer sumOfMoney;		//累计消费金额
    private Integer currentScore;	//当前积分

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSumOfMoney() {
        return sumOfMoney;
    }

    public void setSumOfMoney(Integer sumOfMoney) {
        this.sumOfMoney = sumOfMoney;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
    }
}
