package com.bhallg.app.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class MonetaryAmount {
	
	private static final Currency USD = Currency.getInstance("USD");
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;
    
	private Currency currency;
	private BigDecimal amount;
	
	MonetaryAmount(Currency currency, BigDecimal amount) {
		this(currency, amount, DEFAULT_ROUNDING);
	}
	
	MonetaryAmount(Currency currency, BigDecimal amount, RoundingMode rounding){
		this.currency = currency;
		this.amount = amount.setScale(currency.getDefaultFractionDigits(), rounding);
	}
	
	public static MonetaryAmount dollars(BigDecimal amount) {
		return new MonetaryAmount(USD, amount);
	}

	public Currency getCurrency() {
        return currency;
    }
	
	public void setCurrency(Currency currency) {
        this.currency = currency;
    }

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	

}
