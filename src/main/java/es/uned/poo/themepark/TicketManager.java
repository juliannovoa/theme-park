package es.uned.poo.themepark;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketManager {

	private static TicketManager instance;

	private final List<Sale> sales;

	private Sale currentSale;

	private TicketManager() {

		final RateManager rates = RateManager.getInstance();
		rates.initialiseSample();
		sales = new ArrayList<>();
		currentSale = null;

	}

	public static TicketManager getInstance() {
		if (instance == null) {
			instance = new TicketManager();
		}
		return instance;
	}

	public void startNewSale() {
		currentSale = new Sale();
	}

	public void addNewDailyTicket(Customer customer, boolean isVip, LocalDate date) {
		currentSale.addTicket(new DailyTicket(customer, isVip, date));
	}

	public void addNewSeasonTicket(Customer customer, boolean isVip, int year, Season season) {
		currentSale.addTicket(new SeasonTicket(customer, isVip, season, year));
	}

	public void finishSale() {
		if (currentSale.isCorrect()) {
			sales.add(currentSale);
			currentSale = null;
		} else {
			throw new IllegalArgumentException("This sale cannot be finished. An adult ticket must be added.");
		}
	}

}
