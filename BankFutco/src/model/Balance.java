package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Balance {
    private String accountNumber;
    private LocalDate date;
    private BalanceType balanceType;
    private BigDecimal cashIn;
    private BigDecimal cashOut;
    private BigDecimal closingBalance;

    public Balance() {
    }

    public Balance(String accountNumber, LocalDate date, BalanceType balanceType, BigDecimal cashIn, BigDecimal cashOut, BigDecimal closingBalance) {
        this.accountNumber = accountNumber;
        this.date = date;
        this.balanceType = balanceType;
        this.cashIn = cashIn;
        this.cashOut = cashOut;
        this.closingBalance = closingBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BalanceType getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(BalanceType balanceType) {
        this.balanceType = balanceType;
    }

    public BigDecimal getCashIn() {
        return cashIn;
    }

    public void setCashIn(BigDecimal cashIn) {
        this.cashIn = cashIn;
    }

    public BigDecimal getCashOut() {
        return cashOut;
    }

    public void setCashOut(BigDecimal cashOut) {
        this.cashOut = cashOut;
    }

    public BigDecimal getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(BigDecimal closingBalance) {
        this.closingBalance = closingBalance;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "accountNumber='" + accountNumber + '\'' +
                ", date=" + date +
                ", balanceType=" + balanceType +
                ", cashIn=" + cashIn +
                ", cashOut=" + cashOut +
                ", closingBalance=" + closingBalance +
                '}';
    }
}
