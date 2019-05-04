package es.uned.poo.themepark;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SeasonTicket extends Ticket {

	private final Season season;
	private final int year;
	private LocalDate useDate;

	public SeasonTicket(Customer customer, boolean isVip, Season season, int year) {
		super(customer, isVip);
		this.season = season;
		this.year = year;
		useDate = null;

		final RateManager rm = RateManager.getInstance();

		final BigDecimal basicRate = rm.getTicketBasicRate(season, year);
		BigDecimal personalDiscount = customer.getDiscount();
		final BigDecimal maxDiscount = rm.getMaxDiscount();
		if (personalDiscount.compareTo(maxDiscount) == 1) {
			personalDiscount = maxDiscount;
		}

		this.setDiscount(personalDiscount);
		this.setRate(basicRate);
		this.calculatePrice();
	}

	public void setUseDate(LocalDate date) {
		final RateManager rm = RateManager.getInstance();
		if (date.getYear() != year || !rm.getSeasonByDate(date).equals(season)) {
			throw new IllegalArgumentException("Fecha no válida para esta entrada");
		}
		useDate = date;
	}

	@Override
	public String toString() {
		final StringBuilder output = new StringBuilder();
		output.append("*******************************************\n");
		output.append("*  Entrada para el parque de atracciones  *\n");
		output.append("*******************************************\n\n");
		output.append("Fecha: Entrada de temporada.\n");
		output.append("       Válida en " + season.getDescription() + " del año " + year + "\n\n");
		output.append(super.toString());

		return output.toString();
	}

	@Override
	public LocalDate getUseDate() {
		return useDate;
	}

}
