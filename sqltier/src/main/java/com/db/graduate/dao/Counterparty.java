package com.db.graduate.dao;

import java.sql.Date;

public class Counterparty {
    private Long counterpartyId;
    private String counterpartyName;
    private String counterpartyStatus;
    private Date counterpartyDateRegistered;

    public Long getCounterpartyId() {
        return counterpartyId;
    }

    public void setCounterpartyId(Long counterpartyId) {
        this.counterpartyId = counterpartyId;
    }

    public String getCounterpartyName() {
        return counterpartyName;
    }

    public void setCounterpartyName(String counterpartyName) {
        this.counterpartyName = counterpartyName;
    }

    public String getCounterpartyStatus() {
        return counterpartyStatus;
    }

    public void setCounterpartyStatus(String counterpartyStatus) {
        this.counterpartyStatus = counterpartyStatus;
    }

    public Date getCounterpartyDateRegistered() {
        return counterpartyDateRegistered;
    }

    public void setCounterpartyDateRegistered(Date counterpartyDateRegistered) {
        this.counterpartyDateRegistered = counterpartyDateRegistered;
    }
}
