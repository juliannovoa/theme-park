package es.uned.poo.themepark;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class TicketReport {
	/** The Constant MONTHS_IN_YEAR. */
	private final static int MONTHS_IN_YEAR = 12;

	/** The Constant WEEKS_IN_YEAR. */
	private final static int WEEKS_IN_YEAR = 53;

	/** The Constant DATE_FORMAT. */
	private final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/** The Constant CURRENCY. */
	private final static String CURRENCY = "€";

	/** The Constant ROUNDING_MODE. */
	private final static RoundingMode ROUNDING_MODE = RoundingMode.CEILING;

	/** The rides. */
	private final List<Sale> sales;

	private final SortedMap<LocalDate, BigDecimal> dailyBilling;

	private final SortedMap<LocalDate, Integer> dailyUsers;

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
	public TicketReport(List<Sale> sales, int year) {
		this.sales = sales;
		this.year = year;
		this.firstDayOfYear = LocalDate.of(year, 1, 1);
		this.lastDayOfYear = LocalDate.of(year, 12, 31);
		yearLength = firstDayOfYear.isLeapYear() ? 366 : 365;
		dailyBilling = new TreeMap<>();
		dailyUsers = new TreeMap<>();
	}

	/**
	 * Gets the report.
	 *
	 * @return the report
	 */
	public String generateReport() {
		final StringBuilder output = new StringBuilder();
		final StringBuilder dailyOutput = new StringBuilder();
		analizeData();

		BigDecimal totalBilling = new BigDecimal(0);
		int totalUsers = 0;

		final BigDecimal[] billingPerWeek = new BigDecimal[WEEKS_IN_YEAR];
		final int[] usersPerWeek = new int[WEEKS_IN_YEAR];
		final BigDecimal[] billingPerMonth = new BigDecimal[MONTHS_IN_YEAR];
		final int[] usersPerMonth = new int[MONTHS_IN_YEAR];

		initialise(billingPerWeek);
		initialise(billingPerMonth);

		dailyOutput.append("Informe diario de venta de entradas:\n");

		for (LocalDate date = firstDayOfYear; !date.isAfter(lastDayOfYear); date = date.plusDays(1)) {
			final int dayOfYear = date.getDayOfYear();
			final int weekNumber = calculateWeekNumber(dayOfYear);
			final int monthNumber = date.getMonthValue();
			final BigDecimal billing;
			final int users;
			final BigDecimal averageTicketPrice;

			if (dailyBilling.containsKey(date)) {
				billing = dailyBilling.get(date);
				users = dailyUsers.get(date);
				averageTicketPrice = billing.divide(BigDecimal.valueOf(users));
			} else {
				billing = BigDecimal.ZERO;
				users = 0;
				averageTicketPrice = BigDecimal.ZERO;
			}

			dailyOutput.append(date.format(DATE_FORMAT) + ": Facturación:" + billing.setScale(2, RoundingMode.CEILING)
					+ CURRENCY + ", Usuarios: " + users + ", Precio medio de la entrada: "
					+ averageTicketPrice.setScale(2, RoundingMode.CEILING) + CURRENCY + "\n");

			totalBilling = totalBilling.add(billing);
			billingPerWeek[weekNumber - 1] = billingPerWeek[weekNumber - 1].add(billing);
			billingPerMonth[monthNumber - 1] = billingPerMonth[monthNumber - 1].add(billing);

			totalUsers += users;
			usersPerWeek[weekNumber - 1] += users;
			usersPerMonth[monthNumber - 1] += users;

		}

		output.append("Informe de venta de entradas del año " + year + "\n\n");
		output.append(generateAverageReport(totalBilling, totalUsers));
		output.append(generateMonthlyReport(billingPerMonth, usersPerMonth));
		output.append(generateWeeklyReport(billingPerWeek, usersPerWeek));
		output.append(dailyOutput);

		return output.toString();
	}

	/**
	 * Initialise.
	 *
	 * @param costArray
	 *            the cost array
	 */
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
	 * @param usersPerWeek
	 * @return the object
	 */
	private Object generateWeeklyReport(BigDecimal[] billingPerWeek, int[] usersPerWeek) {
		final StringBuilder output = new StringBuilder();
		output.append("Gasto desglosado por seamanas:\n");
		for (int i = 1; i <= WEEKS_IN_YEAR; i++) {
			final BigDecimal billing = billingPerWeek[i - 1];
			BigDecimal averageTicketPrice;
			final int users = usersPerWeek[i - 1];
			averageTicketPrice = users == 0 ? BigDecimal.ZERO : billing.divide(BigDecimal.valueOf(users));

			output.append("Semana " + i + ": " + "Facturación:" + billing.setScale(2, RoundingMode.CEILING) + CURRENCY
					+ ", Usuarios: " + users + ", Precio medio de la entrada: "
					+ averageTicketPrice.setScale(2, RoundingMode.CEILING) + CURRENCY + "\n");
		}

		return output.toString();
	}

	/**
	 * Generate monthly cost.
	 *
	 * @param costPerMonth
	 *            the cost per month
	 * @param usersPerMonth
	 * @return the string
	 */
	private String generateMonthlyReport(BigDecimal[] billingPerMonth, int[] usersPerMonth) {
		final StringBuilder output = new StringBuilder();
		output.append("Venta de entradas desglosada por meses:\n");
		for (int i = 1; i <= MONTHS_IN_YEAR; i++) {
			final BigDecimal billing = billingPerMonth[i - 1];
			BigDecimal averageTicketPrice;
			final int users = usersPerMonth[i - 1];
			averageTicketPrice = users == 0 ? BigDecimal.ZERO : billing.divide(BigDecimal.valueOf(users));

			output.append(getMonthName(i) + ": " + "Facturación:" + billing.setScale(2, RoundingMode.CEILING) + CURRENCY
					+ ", Usuarios: " + users + ", Precio medio de la entrada: "
					+ averageTicketPrice.setScale(2, RoundingMode.CEILING) + CURRENCY + "\n");
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
	 * @param totalBilling
	 *            the total cost
	 * @param totalUsers
	 * @return the string
	 */
	private String generateAverageReport(BigDecimal totalBilling, int totalUsers) {

		final StringBuilder output = new StringBuilder();

		output.append("Facturación total anual: " + totalBilling.setScale(2) + CURRENCY + "\n");
		output.append("Facturación media mensual: "
				+ totalBilling.divide(BigDecimal.valueOf(MONTHS_IN_YEAR), 2, ROUNDING_MODE) + CURRENCY + "\n");
		output.append("Facturación media semanal: "
				+ totalBilling.divide(BigDecimal.valueOf(WEEKS_IN_YEAR), 2, ROUNDING_MODE) + CURRENCY + "\n");
		output.append("Facturación media diaria: "
				+ totalBilling.divide(BigDecimal.valueOf(yearLength), 2, ROUNDING_MODE) + CURRENCY + "\n\n");
		output.append("Clientes totales: " + totalUsers + "\n");
		output.append("Clientes medios mensuales: " + totalUsers / MONTHS_IN_YEAR + "\n");
		output.append("Clientes medios semanales: " + totalUsers / WEEKS_IN_YEAR + "\n");
		output.append("Clientes medios diarios: " + totalUsers / yearLength + "\n\n");
		output.append("Precio medio de las entradas: "
				+ totalBilling.divide(BigDecimal.valueOf(totalUsers), 2, ROUNDING_MODE) + CURRENCY + "\n");

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
	private void analizeData() {

		final Iterator<Sale> it = sales.iterator();
		while (it.hasNext()) {
			final Sale sl = it.next();
			final SortedMap<LocalDate, BigDecimal> billingByDate = sl.getTotalBillingByDate();
			final SortedMap<LocalDate, Integer> usersByDate = sl.getNumberOfTicketsByDate();
			for (final LocalDate date : billingByDate.keySet()) {
				if (date.getYear() == year) {
					BigDecimal billing = billingByDate.get(date);
					if (dailyBilling.containsKey(date)) {
						billing = billing.add(dailyBilling.get(date));
					}
					dailyBilling.put(date, billing);

					int users = usersByDate.get(date);
					if (dailyUsers.containsKey(date)) {
						users += dailyUsers.get(date);
					}
					dailyUsers.put(date, users);
				}
			}

		}

	}
}
