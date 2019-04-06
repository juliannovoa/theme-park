package es.uned.poo.themepark;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

// TODO: Auto-generated Javadoc
/**
 * The Class RideReport.
 */
public class RideReport {

	/** The rides. */
	private final List<Ride> rides;

	/** The workforce parameters. */
	private final WorkforceParameters workforceParameters;

	/** The year. */
	private final int year;

	private final LocalDate firstDayOfYear;

	/**
	 * Instantiates a new ride report.
	 *
	 * @param rides
	 *            the rides
	 * @param workforceParameters
	 *            the workforce parameters
	 * @param year
	 *            the year
	 */
	public RideReport(List<Ride> rides, WorkforceParameters workforceParameters, int year) {
		this.rides = rides;
		this.workforceParameters = workforceParameters;
		this.year = year;
		this.firstDayOfYear = LocalDate.of(year, 1, 1);
	}

	/**
	 * Gets the report.
	 *
	 * @return the report
	 */
	public String getReport() {
		final SortedMap<LocalDate, BigDecimal> dailyCost = getDailyCost();
		return null;
	}

	/**
	 * Gets the daily workers.
	 *
	 * @return the daily workers
	 */
	private SortedMap<LocalDate, BigDecimal> getDailyCost() {
		final SortedMap<LocalDate, int[]> dailyWorkers = new TreeMap<>();
		for (final Ride ride : rides) {
			final SortedSet<TimePeriod> periods = ride.getActivePeriodByYear(year);
			for (final TimePeriod period : periods) {
				final LocalDate end = period.getEnd();
				final LocalDate start = period.getStart();
				LocalDate date = start.isAfter(firstDayOfYear) ? start : firstDayOfYear;
				for (; !date.isAfter(end) && date.getYear() == year; date = date.plusDays(1)) {
					if (!dailyWorkers.containsKey(date)) {
						final int[] workers = new int[2];
						dailyWorkers.put(date, workers);
					}
					final int[] workers = dailyWorkers.get(date);
					workers[0] += ride.getResponsibles();
					workers[1] += ride.getAssistants();
					dailyWorkers.put(date, workers);
				}
			}
		}
		final SortedMap<LocalDate, BigDecimal> dailyCost = new TreeMap<>();
		return dailyCost;
	}

}
