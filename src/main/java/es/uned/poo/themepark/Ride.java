package es.uned.poo.themepark;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

// TODO: Auto-generated Javadoc
/**
 * The Class Ride.
 */
public class Ride {

	/** The id. */
	private final String id;

	/** The type. */
	private final RideType type;

	/** The active periods. */
	private final SortedSet<TimePeriod> activePeriods;

	/**
	 * Constructor for objects of class Ride.
	 *
	 * @param id
	 *            the id
	 * @param type
	 *            the type
	 */
	public Ride(String id, RideType type) {
		this.id = id;
		this.type = type;
		activePeriods = new TreeSet<>();
	}

	/**
	 * Adds the active period.
	 *
	 * @param start
	 *            the begin
	 * @param end
	 *            the end
	 */
	public void addActivePeriod(LocalDate start, LocalDate end) {

		final TimePeriod candidate = new TimePeriod(start, end);
		final Iterator<TimePeriod> it = activePeriods.iterator();
		while (it.hasNext()) {
			final TimePeriod period = it.next();
			if (period.isAfter(candidate)) {
				break;
			} else if (candidate.overlap(period)) {
				candidate.extendPeriod(period);
				it.remove();
			}
		}
		activePeriods.add(candidate);
	}

	/**
	 * Gets the active period by year.
	 *
	 * @param year
	 *            the year
	 * @return the active period by year
	 */
	public SortedSet<TimePeriod> getActivePeriodByYear(int year) {
		final SortedSet<TimePeriod> periods = new TreeSet<>();
		final Iterator<TimePeriod> it = activePeriods.iterator();
		while (it.hasNext()) {
			final TimePeriod period = it.next();
			if (period.afterYear(year)) {
				break;
			} else if (period.inYear(year)) {
				periods.add(period);
			}
		}
		return periods;
	}

	/**
	 * Gets the assistants.
	 *
	 * @return the assistants
	 */
	public int getAssistants() {
		return type.getAssitants();
	}

	/**
	 * Gets the responsibles.
	 *
	 * @return the responsibles
	 */
	public int getResponsibles() {
		return type.getResponsibles();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getID() {
		return id;
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @return the sum of x and y
	 */
	@Override
	public String toString() {

		final StringBuilder output = new StringBuilder();
		output.append("======================================\n");
		output.append("Ficha de la atracción " + id + "\n");
		output.append("======================================\n");
		return output.toString() + type.toString() + "\n\n";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Ride)) {
			return false;
		}

		return id.equals(((Ride) o).getID());
	}

}
