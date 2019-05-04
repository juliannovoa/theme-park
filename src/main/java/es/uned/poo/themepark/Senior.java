package es.uned.poo.themepark;

import java.math.BigDecimal;

public class Senior extends Customer {

	private final boolean disabled;
	private final boolean unemployed;

	public Senior(String name, Boolean disabled, Boolean unemployed) {
		super(name, false);
		this.disabled = disabled;
		this.unemployed = unemployed;

		final RateManager rm = RateManager.getInstance();
		BigDecimal discount = rm.getSeniorDiscount();

		if (disabled) {
			discount = mixDiscount(discount, rm.getDisabledDiscount());
		}

		if (unemployed) {
			discount = mixDiscount(discount, rm.getDisabledDiscount());
		}

		this.setDiscount(discount);
	}

	@Override
	public String getCostumerDisciountDescription() {
		final StringBuilder output = new StringBuilder();
		output.append("Tarifa y descuentos aplicados:\n");
		output.append("   -Tarifa senior.\n");
		if (disabled) {
			output.append("   -Descuento por diversidad funcional.\n");
		}

		if (unemployed) {
			output.append("   -Descuento por desempleado.\n");
		}

		return output.toString() + "\n";
	}
}