package es.uned.poo.themepark;

import java.math.BigDecimal;

public class Child extends Customer {

	private final boolean disabled;

	public Child(String name, Boolean disabled) {
		super(name, true);
		this.disabled = disabled;

		final RateManager rm = RateManager.getInstance();
		BigDecimal discount = rm.getChildDiscount();

		if (disabled) {
			discount = mixDiscount(discount, rm.getDisabledDiscount());
		}
		this.setDiscount(discount);
	}

	@Override
	public String getCostumerDisciountDescription() {
		final StringBuilder output = new StringBuilder();
		output.append("Tarifa y descuentos aplicados:\n");
		output.append("   -Tarifa infantil.\n");
		if (disabled) {
			output.append("   -Descuento por diversidad funcional.\n");
		}
		return output.toString() + "\n";
	}
}