package es.uned.poo.themepark;

public class Baby extends Customer {

	public Baby(String name) {
		super(name, true);
		final RateManager rm = RateManager.getInstance();
		super.setDiscount(rm.getBabyDiscount());
	}

	@Override
	public String getCostumerDisciountDescription() {
		final StringBuilder output = new StringBuilder();
		output.append("Tarifa y descuentos aplicados:\n");
		output.append("   -Tarifa bebé.\n\n");
		return output.toString();
	}

}
