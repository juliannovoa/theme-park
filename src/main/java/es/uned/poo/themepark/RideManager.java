package es.uned.poo.themepark;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class RideManager.
 */
public final class RideManager {

	/** The instance. */
	private static RideManager instance;

	/** The catalog. */
	private final RideTypeCatalog catalog;

	/** The rides. */
	private final Map<String, Ride> rides;

	/** The workforce parameters. */
	private WorkforceParameters workforceParameters;

	/**
	 * Constructor for objects of class RideManager.
	 */
	private RideManager() {
		catalog = new RideTypeCatalog();
		rides = new HashMap<>();
		initialiseSample();
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @return the sum of x and y
	 */
	public static RideManager getInstance() {
		if (instance == null) {
			instance = new RideManager();
		}
		return instance;
	}

	/**
	 * Gets the report by year.
	 *
	 * @param year
	 *            the year
	 * @return the report by year
	 */
	public String generateReportByYear(int year) {
		final RideReport report = new RideReport(rides.values(), workforceParameters, year);
		return report.generateReport();
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @return the sum of x and y
	 */
	private void initialiseSample() {

		workforceParameters = new WorkforceParameters(BigDecimal.valueOf(950), BigDecimal.valueOf(1.15),
				BigDecimal.valueOf(1.1), BigDecimal.valueOf(1.2), BigDecimal.valueOf(0.3), BigDecimal.valueOf(0.1));

		addRide("A1", "A");
		addRide("A2", "A");
		addRide("A3", "A");
		addRide("A4", "A");

		addRide("B1", "B");
		addRide("B2", "B");
		addRide("B3", "B");
		addRide("B4", "B");
		addRide("B5", "B");
		addRide("B6", "B");

		addRide("C1", "C");
		addRide("C2", "C");
		addRide("C3", "C");
		addRide("C4", "C");

		addRide("D1", "D");
		addRide("D2", "D");
		addRide("D3", "D");

		addRide("E1", "E");
		addRide("E2", "E");
		addRide("E3", "E");
		addRide("E4", "E");
		addRide("E5", "E");
		addRide("E6", "E");
		addRide("E7", "E");
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @return the sum of x and y
	 */
	public String getRidesDescription() {
		final StringBuilder output = new StringBuilder();
		for (final Ride ride : rides.values()) {
			output.append(ride.toString());
		}
		return output.toString();
	}

	public void addRide(String rideName, String rideType) {
		final RideType type = catalog.getRideType(rideType);
		if (type == null || rides.containsKey(rideName)) {
			throw new IllegalArgumentException("Invalid arguments. Ride could not be created.");
		}
		final Ride ride = new Ride(rideName, type);
		rides.put(rideName, ride);
	}

	public void addWorkingPeriod(String rideID, LocalDate start, LocalDate end) {
		final Ride ride = rides.get(rideID);
		if (ride == null) {
			throw new IllegalArgumentException("Invalid arguments. Ride does not exists.");
		}
		ride.addActivePeriod(start, end);
	}

}
