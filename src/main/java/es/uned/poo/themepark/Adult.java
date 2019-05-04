package es.uned.poo.themepark;

import java.math.BigDecimal;

public class Adult extends Customer {

	private final boolean disabled;
	private final boolean unemployed;
	private final boolean student;

	public Adult(String name, Boolean disabled, Boolean unemployed, Boolean student) {
		super(name, false);
		this.disabled = disabled;
		this.unemployed = unemployed;
		this.student = student;

		final RateManager rm = RateManager.getInstance();
		BigDecimal discount = BigDecimal.ZERO;

		if (disabled) {
			discount = mixDiscount(discount, rm.getDisabledDiscount());
		}

		if (unemployed) {
			discount = mixDiscount(discount, rm.getDisabledDiscount());
		}

		if (student) {
			discount = mixDiscount(discount, rm.getStudentDiscount());
		}

		this.setDiscount(discount);
	}

	@Override
	public String getCostumerDisciountDescription() {
		final StringBuilder output = new StringBuilder();
		output.append("Tarifa y descuentos aplicados:\n");
		output.append("   -Tarifa adulto.\n");
		if (disabled) {
			output.append("   -Descuento por diversidad funcional.\n");
		}

		if (unemployed) {
			output.append("   -Descuento por desempleado.\n");
		}

		if (student) {
			output.append("   -Descuento por estudiente.\n");
		}
		return output.toString() + "\n";
	}

}