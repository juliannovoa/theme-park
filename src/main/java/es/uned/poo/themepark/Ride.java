package es.uned.poo.themepark;

/**
 * Write a description of class Ride here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ride {
	private final String id;
	private final RideType type;

	/**
	 * Constructor for objects of class Ride
	 */
	public Ride(String id, RideType type) {
		this.id = id;
		this.type = type;
	}

	/**
	 * An example of a method - replace this comment with your own
	 * 
	 * @param y
	 *            a sample parameter for a method
	 * @return the sum of x and y
	 */
	@Override
	public String toString() {
		String output = "======================================\n";
		output += "Ficha de la atracción " + id + "\n";
		output += "======================================\n";
		return output + type.toString() + "\n\n";
	}

}
