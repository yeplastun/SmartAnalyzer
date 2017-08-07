package com.db.graduate.dao;

public class Deal {
    private Long dealId;
    private String dealTime;
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

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
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
