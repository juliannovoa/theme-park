package es.uned.poo.themepark;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class RideTypeCatalog.
 */
public class RideTypeCatalog {

	/** The ride types. */
	private final Map<String, RideType> rideTypes;

	/**
	 * Constructor for objects of class RideTypeCatalog.
	 */
	public RideTypeCatalog() {
		rideTypes = new HashMap<>();
		initialiseSample();
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @param typeID
	 *            the type ID
	 * @return the sum of x and y
	 */
	public RideType getRideType(String typeID) {
		return rideTypes.get(typeID);
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @return the sum of x and y
	 */
	private void initialiseSample() {
		final RideType a = new RideType("A", 6, 1).withChildrenUse().withAdultUse().withMinHeight(1.20).withVipUse();

		final RideType b = new RideType("B", 5, 1).withAdultUse().withMaxHeight(1.90).withMinHeight(1.2);

		final RideType c = new RideType("C", 3, 1).withChildrenUse().withMaxHeight(1.20);

		final RideType d = new RideType("D", 5, 1).withChildrenUse().withAdultUse().withVipUse();

		final RideType e = new RideType("E", 7, 1).withAdultUse().withVipUse();

		rideTypes.put(a.getType(), a);
		rideTypes.put(b.getType(), b);
		rideTypes.put(c.getType(), c);
		rideTypes.put(d.getType(), d);
		rideTypes.put(e.getType(), e);
	}
}
