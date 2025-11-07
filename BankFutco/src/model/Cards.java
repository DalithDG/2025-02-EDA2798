package model;

import java.math.BigDecimal;

public class Cards {

    private String cardNumber;
    private String Type; // Debit - Credit
    private BigDecimal totalLimit;
    private BigDecimal amountUsed;
    private BigDecimal available;


    public Cards(String cardNumber, String Type, BigDecimal totalLimit, BigDecimal amountUsed, BigDecimal available) {
        this.cardNumber = cardNumber;
        this.Type = Type;
        this.totalLimit = totalLimit;
        this.amountUsed = amountUsed;
        this.available = available;
    }
    

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getType() {
        return this.Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public BigDecimal getTotalLimit() {
        return this.totalLimit;
    }

    public void setTotalLimit(BigDecimal totalLimit) {
        this.totalLimit = totalLimit;
    }

    public BigDecimal getAmountUsed() {
        return this.amountUsed;
    }

    public void setAmountUsed(BigDecimal amountUsed) {
        this.amountUsed = amountUsed;
    }

    public BigDecimal getAvailable() {
        return this.available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }


    @Override
    public String toString() {
        return "Cards [cardNumber=" + cardNumber + ", Type=" + Type + ", totalLimit=" + totalLimit + ", amountUsed="
                + amountUsed + ", available=" + available + "]";
    }

    

}
