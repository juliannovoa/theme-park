package es.uned.poo.themepark;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

/**
 * The Class RideReport.
 */
/**
 * @author julian
 *
 */
public class RideReport {

	/** The Constant MONTHS_IN_YEAR. */
	private final static int MONTHS_IN_YEAR = 12;

	/** The Constant WEEKS_IN_YEAR. */
	private final static int WEEKS_IN_YEAR = 53;

	/** The Constant DATE_FORMAT. */
	private final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/** The Constant CURRENCY. */
	private final static String CURRENCY = "€";

	private final static RoundingMode ROUNDING_MODE = RoundingMode.CEILING;

	/** The rides. */
	private final Collection<Ride> rides;

	/** The workforce parameters. */
	private final WorkforceParameters workforceParameters;

	/** The year. */
	private final int year;

	/** The first day of year. */
	private final LocalDate firstDayOfYear;

	/** The last day of year. */
	private final LocalDate lastDayOfYear;

	/** The year length. */
	private final int yearLength;

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
	public RideReport(Collection<Ride> rides, WorkforceParameters workforceParameters, int year) {
		this.rides = rides;
		this.workforceParameters = workforceParameters;
		this.year = year;
		this.firstDayOfYear = LocalDate.of(year, 1, 1);
		this.lastDayOfYear = LocalDate.of(year, 12, 31);
		yearLength = firstDayOfYear.isLeapYear() ? 366 : 365;
	}

	/**
	 * Gets the report.
	 *
	 * @return the report
	 */
	public String generateReport() {
		final StringBuilder output = new StringBuilder();
		final StringBuilder dailyOutput = new StringBuilder();
		final SortedMap<LocalDate, BigDecimal> dailyCost = getDailyCost();

		BigDecimal totalCost = new BigDecimal(0);
		final BigDecimal[] costPerWeek = new BigDecimal[WEEKS_IN_YEAR];
		final BigDecimal[] costPerMonth = new BigDecimal[MONTHS_IN_YEAR];

		initialise(costPerWeek);
		initialise(costPerMonth);

		dailyOutput.append("Gastos diarios:\n");

		for (LocalDate date = firstDayOfYear; !date.isAfter(lastDayOfYear); date = date.plusDays(1)) {
			final int dayOfYear = date.getDayOfYear();
			final int weekNumber = calculateWeekNumber(dayOfYear);
			final int monthNumber = date.getMonthValue();
			final BigDecimal cost;

			if (dailyCost.containsKey(date)) {
				cost = dailyCost.get(date);
			} else {
				cost = new BigDecimal(0);
			}

			dailyOutput
					.append(date.format(DATE_FORMAT) + ": " + cost.setScale(2, RoundingMode.CEILING) + CURRENCY + "\n");

			totalCost = totalCost.add(cost);
			costPerWeek[weekNumber - 1] = costPerWeek[weekNumber - 1].add(cost);
			costPerMonth[monthNumber - 1] = costPerMonth[monthNumber - 1].add(cost);

		}

		output.append("Informe de gastos de personal del año " + year + "\n\n");
		output.append(generateAverageCost(totalCost));
		output.append(generateMonthlyCost(costPerMonth));
		output.append(generateWeeklyCost(costPerWeek));
		output.append(dailyOutput);

		return output.toString();
	}

	private void initialise(BigDecimal[] costArray) {
		for (int i = 0; i < costArray.length; i++) {
			costArray[i] = new BigDecimal(0);
		}
	}

	/**
	 * Generate weekly cost.
	 *
	 * @param costPerWeek
	 *            the cost per week
	 * @return the object
	 */
	private Object generateWeeklyCost(BigDecimal[] costPerWeek) {
		final StringBuilder output = new StringBuilder();
		output.append("Gasto desglosado por semanas:\n");
		for (int i = 1; i <= WEEKS_IN_YEAR; i++) {
			output.append("Semana " + i + ": " + costPerWeek[i - 1].setScale(2, ROUNDING_MODE) + CURRENCY + "\n");
		}

		return output.toString();
	}

	/**
	 * Generate monthly cost.
	 *
	 * @param costPerMonth
	 *            the cost per month
	 * @return the string
	 */
	private String generateMonthlyCost(BigDecimal[] costPerMonth) {
		final StringBuilder output = new StringBuilder();
		output.append("Gasto desglosado por meses:\n");
		for (int i = 1; i <= MONTHS_IN_YEAR; i++) {
			output.append(getMonthName(i) + ": " + costPerMonth[i - 1].setScale(2, ROUNDING_MODE) + CURRENCY + "\n");
		}

		return output.toString();
	}

	/**
	 * Gets the month name.
	 *
	 * @param i
	 *            the i
	 * @return the month name
	 */
	private String getMonthName(int i) {
		String month = null;
		switch (i) {
		case 1:
			month = "Enero";
			break;
		case 2:
			month = "Febrero";
			break;
		case 3:
			month = "Marzo";
			break;
		case 4:
			month = "Abril";
			break;
		case 5:
			month = "Mayo";
			break;
		case 6:
			month = "Junio";
			break;
		case 7:
			month = "Julio";
			break;
		case 8:
			month = "Agosto";
			break;
		case 9:
			month = "Septiembre";
			break;
		case 10:
			month = "Octubre";
			break;
		case 11:
			month = "Noviembre";
			break;
		case 12:
			month = "Diciembre";
			break;
		default:
			break;
		}

		return month;
	}

	/**
	 * Generate average cost.
	 *
	 * @param totalCost
	 *            the total cost
	 * @return the string
	 */
	private String generateAverageCost(BigDecimal totalCost) {

		final StringBuilder output = new StringBuilder();

		output.append("Gasto total anual: " + totalCost.setScale(2) + CURRENCY + "\n");
		output.append("Gasto medio mensual: " + totalCost.divide(BigDecimal.valueOf(MONTHS_IN_YEAR), 2, ROUNDING_MODE)
				+ CURRENCY + "\n");
		output.append("Gasto medio semanal: " + totalCost.divide(BigDecimal.valueOf(WEEKS_IN_YEAR), 2, ROUNDING_MODE)
				+ CURRENCY + "\n");
		output.append("Gasto medio diario: " + totalCost.divide(BigDecimal.valueOf(yearLength), 2, ROUNDING_MODE)
				+ CURRENCY + "\n");
		return output.toString();
	}

	/**
	 * Calculate week number.
	 *
	 * @param dayOfYear
	 *            the day of year
	 * @return the int
	 */
	private int calculateWeekNumber(int dayOfYear) {
		return (dayOfYear + firstDayOfYear.getDayOfWeek().getValue() - 1) / 7 + 1;
	}

	/**
	 * Gets the daily workers.
	 *
	 * @return the daily workers
	 */
	private SortedMap<LocalDate, BigDecimal> getDailyCost() {
		final Map<LocalDate, int[]> dailyWorkers = new HashMap<>();
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
		for (final LocalDate date : dailyWorkers.keySet()) {
			final int[] workers = dailyWorkers.get(date);
			dailyCost.put(date, workforceParameters.calculateDailyCost(workers[0], workers[1]));
		}

		return dailyCost;
	}

}
