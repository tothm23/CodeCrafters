package com.codecrafterswebshop.Model;

/**
 *
 * @author tothm23
 */
public class Payment {

    private String customerName;
    private String customerEmail;
    private Long amount;
    private String cardNumber;
    private String cardExpMonth;
    private String cardExpYear;
    private String cardCvc;

    public Payment() {
    }

    public Payment(String customerName, String customerEmail, Long amount, String cardNumber, String cardExpMonth, String cardExpYear, String cardCvc) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.amount = amount;
        this.cardNumber = cardNumber;
        this.cardExpMonth = cardExpMonth;
        this.cardExpYear = cardExpYear;
        this.cardCvc = cardCvc;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpMonth() {
        return cardExpMonth;
    }

    public void setCardExpMonth(String cardExpMonth) {
        this.cardExpMonth = cardExpMonth;
    }

    public String getCardExpYear() {
        return cardExpYear;
    }

    public void setCardExpYear(String cardExpYear) {
        this.cardExpYear = cardExpYear;
    }

    public String getCardCvc() {
        return cardCvc;
    }

    public void setCardCvc(String cardCvc) {
        this.cardCvc = cardCvc;
    }

}
