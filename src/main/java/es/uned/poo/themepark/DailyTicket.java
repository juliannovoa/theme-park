package es.uned.poo.themepark;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DailyTicket extends Ticket {

	private final LocalDate date;

	private final static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public DailyTicket(Customer customer, boolean isVip, LocalDate date) {
		super(customer, isVip);
		this.date = date;

		final RateManager rm = RateManager.getInstance();

		final BigDecimal basicRate = rm.getTicketBasicRate(date);

		BigDecimal personalDiscount = customer.getDiscount();

		if (rm.isBusinessDay(date)) {
			final BigDecimal dayDiscount = BigDecimal.ONE.subtract(rm.getBusinessDayDiscount());
			final BigDecimal totalDiscount = BigDecimal.ONE.subtract(personalDiscount).multiply(dayDiscount);
			personalDiscount = BigDecimal.ONE.subtract(totalDiscount);
		}

		final BigDecimal maxDiscount = rm.getMaxDiscount();
		if (personalDiscount.compareTo(maxDiscount) == 1) {
			personalDiscount = maxDiscount;
		}

		this.setDiscount(personalDiscount);
		this.setRate(basicRate);
		this.calculatePrice();
	}

	@Override
	public String toString() {
		final StringBuilder output = new StringBuilder();
		output.append("*******************************************\n");
		output.append("*  Entrada para el parque de atracciones  *\n");
		output.append("*******************************************\n\n");

		output.append("Fecha: " + date.format(format) + "\n\n");
		output.append(super.toString());

		return output.toString();
	}

	@Override
	public LocalDate getUseDate() {
		return date;
	}

}
