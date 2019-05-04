package es.uned.poo.themepark;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public abstract class Ticket {

	/** The Constant CURRENCY. */
	private final static String CURRENCY = "€";

	/** The Constant ROUNDING_MODE. */
	private final static RoundingMode ROUNDING_MODE = RoundingMode.CEILING;

	private final Customer costumer;
	private final boolean isVip;
	private BigDecimal rate;
	private BigDecimal discount;
	private BigDecimal finalPrice;

	public Ticket(Customer costumer, boolean isVip) {
		this.costumer = costumer;
		this.isVip = isVip;
		rate = null;
		finalPrice = null;

	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	protected void calculatePrice() {
		finalPrice = rate.multiply(BigDecimal.ONE.subtract(discount));
		if (isVip) {
			final RateManager rm = RateManager.getInstance();
			finalPrice = finalPrice.add(BigDecimal.ONE.subtract(rm.getVipRate()));
		}
	}

	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public boolean underageTicket() {
		return costumer.isUnderage();
	}

	public abstract LocalDate getUseDate();

	@Override
	public String toString() {
		final StringBuilder output = new StringBuilder();
		if (isVip) {
			output.append("*******************************************\n");
			output.append("*               Entrada VIP               *\n");
			output.append("*******************************************\n\n");
		}
		output.append("Usuario: " + costumer.getName() + "\n");
		output.append(costumer.getCostumerDisciountDescription());
		output.append("Precio de la entrada: " + finalPrice.setScale(2, ROUNDING_MODE) + CURRENCY + "\n\n");

		return output.toString();
	}

}
