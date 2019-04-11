package es.uned.poo.themepark;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class RideManager.
 */
public final class RideManager {

	/** The instance. */
	private static RideManager instance;

	/** The catalog. */
	private final RideTypeCatalog catalog;

	/** The rides. */
	private final List<Ride> rides;

	/** The workforce parameters. */
	private WorkforceParameters workforceParameters;

	/**
	 * Constructor for objects of class RideManager.
	 */
	private RideManager() {
		catalog = new RideTypeCatalog();
		rides = new ArrayList<>();
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
	public String getReportByYear(int year) {
		final RideReport report = new RideReport(rides, workforceParameters, year);
		return report.generateReport();
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @return the sum of x and y
	 */
	private void initialiseSample() {

		RideType type;

		workforceParameters = new WorkforceParameters(BigDecimal.valueOf(950), BigDecimal.valueOf(0.15),
				BigDecimal.valueOf(0.1), BigDecimal.valueOf(0.2), BigDecimal.valueOf(0.3), BigDecimal.valueOf(0.1));

		type = catalog.getRideType("A");
		rides.add(new Ride("A1", type));
		rides.add(new Ride("A2", type));
		rides.add(new Ride("A3", type));
		rides.add(new Ride("A4", type));

		type = catalog.getRideType("B");
		rides.add(new Ride("B1", type));
		rides.add(new Ride("B2", type));
		rides.add(new Ride("B3", type));
		rides.add(new Ride("B4", type));
		rides.add(new Ride("B5", type));
		rides.add(new Ride("B6", type));

		type = catalog.getRideType("C");
		rides.add(new Ride("C1", type));
		rides.add(new Ride("C2", type));
		rides.add(new Ride("C3", type));
		rides.add(new Ride("C4", type));

		type = catalog.getRideType("D");
		rides.add(new Ride("D1", type));
		rides.add(new Ride("D2", type));
		rides.add(new Ride("D3", type));

		type = catalog.getRideType("E");
		rides.add(new Ride("E1", type));
		rides.add(new Ride("E2", type));
		rides.add(new Ride("E3", type));
		rides.add(new Ride("E4", type));
		rides.add(new Ride("E5", type));
		rides.add(new Ride("E6", type));
		rides.add(new Ride("E7", type));
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @return the sum of x and y
	 */
	public String getRidesDescription() {
		String output = "";
		for (final Ride ride : rides) {
			output += ride.toString();
		}
		return output;
	}

}
