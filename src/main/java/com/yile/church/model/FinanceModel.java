package com.yile.church.model;

import org.omg.PortableInterceptor.INACTIVE;

import java.io.Serializable;
import java.util.Date;

public class FinanceModel implements Serializable {

    public static final Integer FINANCE_INCOME  = 1;
    public static final Integer FINANCE_OUTCOME = 2;

    private Integer id;

    private Integer type;

    private Double money;

    private String item;

    private Date performDate;

    private Date createDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public Date getPerformDate() {
        return performDate;
    }

    public void setPerformDate(Date performDate) {
        this.performDate = performDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    @Override
    public String toString() {
        return "type:"+getType() + " money:"+getMoney() + " item:"+getItem();
    }
}