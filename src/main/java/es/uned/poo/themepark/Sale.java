package es.uned.poo.themepark;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The Class Sale.
 */
public class Sale {

	/** The tickets. */
	private final List<Ticket> tickets;

	/** The adult presence. */
	private boolean adultPresence;

	/**
	 * Instantiates a new sale.
	 */
	public Sale() {
		tickets = new ArrayList<>();
		adultPresence = false;
	}

	/**
	 * Gets the number of tickets.
	 *
	 * @return the number of tickets
	 */
	public SortedMap<LocalDate, Integer> getNumberOfTicketsByDate() {
		final Iterator<Ticket> it = tickets.iterator();
		final SortedMap<LocalDate, Integer> output = new TreeMap<>();
		while (it.hasNext()) {
			final Ticket tk = it.next();
			final LocalDate useDate = tk.getUseDate();
			if (useDate != null) {
				if (output.containsKey(useDate)) {
					final int users = output.get(useDate) + 1;
					output.put(useDate, users);
				} else {
					output.put(useDate, 1);
				}
			}
		}
		return output;
	}

	/**
	 * Gets the total cost.
	 *
	 * @return the total cost
	 */
	public SortedMap<LocalDate, BigDecimal> getTotalBillingByDate() {
		final Iterator<Ticket> it = tickets.iterator();
		final SortedMap<LocalDate, BigDecimal> output = new TreeMap<>();
		while (it.hasNext()) {
			final Ticket tk = it.next();
			final BigDecimal singlePrice = tk.getFinalPrice();
			final LocalDate useDate = tk.getUseDate();
			if (useDate != null) {
				if (output.containsKey(useDate)) {
					final BigDecimal addedPrices = output.get(useDate).add(singlePrice);
					output.put(useDate, addedPrices);
				} else {
					output.put(useDate, singlePrice);
				}
			}
		}
		return output;
	}

	/**
	 * Adds the ticket.
	 *
	 * @param newTicket
	 *            the new ticket
	 */
	public void addTicket(Ticket newTicket) {
		tickets.add(newTicket);
		if (!adultPresence && !newTicket.underageTicket()) {
			adultPresence = true;
		}
	}

	public boolean isCorrect() {
		return adultPresence;
	}

}
