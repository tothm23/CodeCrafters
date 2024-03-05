package com.codecrafterswebshop.Model;

/**
 *
 * @author tothm23
 */
public class CreditCard {

    private String number;
    private String expMonth;
    private String expYear;
    private String cvc;

    public CreditCard() {
    }

    public CreditCard(String number, String expMonth, String expYear, String cvc) {
        this.number = number;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.cvc = cvc;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

}
