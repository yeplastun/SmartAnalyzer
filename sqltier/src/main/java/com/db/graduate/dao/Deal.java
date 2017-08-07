package com.db.graduate.dao;

import java.sql.Date;

public class Deal {
    private Long dealId;
    private Date deal_time;
    private Long dealCounterpartyId;
    private Long dealInstrumentId;
    private String dealType;
    private Double dealAmount;
    private Integer dealQuantity;

    public Long getDealId() {
        return dealId;
    }

    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    public Date getDeal_time() {
        return deal_time;
    }

    public void setDeal_time(Date deal_time) {
        this.deal_time = deal_time;
    }

    public Long getDealCounterpartyId() {
        return dealCounterpartyId;
    }

    public void setDealCounterpartyId(Long dealCounterpartyId) {
        this.dealCounterpartyId = dealCounterpartyId;
    }

    public Long getDealInstrumentId() {
        return dealInstrumentId;
    }

    public void setDealInstrumentId(Long dealInstrumentId) {
        this.dealInstrumentId = dealInstrumentId;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public Double getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(Double dealAmount) {
        this.dealAmount = dealAmount;
    }

    public Integer getDealQuantity() {
        return dealQuantity;
    }

    public void setDealQuantity(Integer dealQuantity) {
        this.dealQuantity = dealQuantity;
    }
}
