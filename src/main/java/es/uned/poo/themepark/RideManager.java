package es.uned.poo.themepark;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class RideManager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public final class RideManager {
	private static RideManager instance;
	private final RideTypeCatalog catalog;
	private final List<Ride> rides;

	/**
	 * Constructor for objects of class RideManager
	 */
	private RideManager() {
		catalog = new RideTypeCatalog();
		rides = new ArrayList<>();
		initialiseSample();
	}

	/**
	 * An example of a method - replace this comment with your own
	 *
	 * @param y
	 *            a sample parameter for a method
	 * @return the sum of x and y
	 */
	public static RideManager getInstance() {
		if (instance == null) {
			instance = new RideManager();
		}
		return instance;
	}

	/**
	 * An example of a method - replace this comment with your own
	 *
	 * @param y
	 *            a sample parameter for a method
	 * @return the sum of x and y
	 */
	private void initialiseSample() {
		RideType type;

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
	 * An example of a method - replace this comment with your own
	 *
	 * @param y
	 *            a sample parameter for a method
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
