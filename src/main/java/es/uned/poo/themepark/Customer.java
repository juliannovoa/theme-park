package es.uned.poo.themepark;

import java.math.BigDecimal;

/**
 * The Class Customer.
 */
public abstract class Customer {

	/** The name. */
	private final String name;

	/** The personal discount. */
	private BigDecimal personalDiscount;

	/** The underage. */
	private final boolean underage;

	/**
	 * Instantiates a new costumer.
	 *
	 * @param name
	 *            the name
	 * @param underage
	 *            is underage
	 */
	public Customer(String name, Boolean underage) {
		this.name = name;
		personalDiscount = null;
		this.underage = underage;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Checks if is underage.
	 *
	 * @return true, if is underage
	 */
	public boolean isUnderage() {
		return underage;
	}

	/**
	 * Sets the discount.
	 *
	 * @param discount
	 *            the new discount
	 */
	protected void setDiscount(BigDecimal discount) {
		this.personalDiscount = discount;
	}

	/**
	 * Gets the discount.
	 *
	 * @return the discount
	 */
	public BigDecimal getDiscount() {
		return personalDiscount;
	}

	/**
	 * Mix discount.
	 *
	 * @param discount1
	 *            the discount 1
	 * @param discount2
	 *            the discount 2
	 * @return the big decimal
	 */
	protected BigDecimal mixDiscount(BigDecimal discount1, BigDecimal discount2) {
		BigDecimal d1 = BigDecimal.ONE.subtract(discount1);
		final BigDecimal d2 = BigDecimal.ONE.subtract(discount2);
		d1 = d1.multiply(d2);
		return BigDecimal.ONE.subtract(d1);

	}

	/**
	 * Gets the costumer disciount description.
	 *
	 * @return the costumer disciount description
	 */
	public abstract String getCostumerDisciountDescription();

}
