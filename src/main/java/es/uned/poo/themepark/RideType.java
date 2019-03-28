package es.uned.poo.themepark;

/**
 * Write a description of class Ride here.
 *
 * @author J. Novoa
 * @version 27/03/2019
 */
public class RideType {
	// instance variables - replace the example below with your own
	private final String type;

	private final int assistants;
	private final int responsibles;
	private boolean childrenUse;
	private boolean adultUse;
	private boolean vip;
	private Double maxHeight;
	private Double minHeight;

	/**
	 * Constructor for objects of class Ride
	 */
	public RideType(String type, int assistants, int responsibles) {
		this.type = type;
		this.assistants = assistants;
		this.responsibles = responsibles;
	}

	/**
	 * An example of a method - replace this comment with your own
	 *
	 * @return the number of assistants assigned to a ride
	 */
	public RideType withAdultUse() {
		adultUse = true;
		return this;
	}

	/**
	 * An example of a method - replace this comment with your own
	 *
	 * @return the number of assistants assigned to a ride
	 */
	public RideType withChildrenUse() {
		childrenUse = true;
		return this;
	}

	/**
	 * An example of a method - replace this comment with your own
	 *
	 * @return the number of assistants assigned to a ride
	 */
	public RideType withMaxHeight(double height) {
		maxHeight = height;
		return this;
	}

	/**
	 * An example of a method - replace this comment with your own
	 *
	 * @return the number of assistants assigned to a ride
	 */
	public RideType withMinHeight(double height) {
		minHeight = height;
		return this;
	}

	/**
	 * An example of a method - replace this comment with your own
	 *
	 * @return the number of assistants assigned to a ride
	 */
	public RideType withVipUse() {
		vip = true;
		return this;
	}

	/**
	 * An example of a method - replace this comment with your own
	 *
	 * @return the number of assistants assigned to a ride
	 */
	public String getType() {
		return type;
	}

	/**
	 * An example of a method - replace this comment with your own
	 *
	 * @return the number of assistants assigned to a ride
	 */
	public int getAssitants() {
		return assistants;
	}

	/**
	 * An example of a method - replace this comment with your own
	 *
	 * @param y
	 *            a sample parameter for a method
	 * @return the sum of x and y
	 */
	public int getResponsibles() {
		return responsibles;
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
		final StringBuilder output = new StringBuilder();

		output.append("Atración de tipo ");
		output.append(type);
		output.append(".\nPuede ser usada por ");
		output.append((adultUse ? "adultos" : ""));
		output.append(((adultUse && childrenUse) ? " y " : ""));
		output.append((childrenUse ? "niños" : ""));
		output.append(".\nEsta atracción");
		output.append((vip ? "" : " no"));
		output.append(" permite suplemento VIP.\n");

		if (minHeight != null) {
			output.append("Altura mínima: ");
			output.append(minHeight);
			output.append("m\n");
		}

		if (maxHeight != null) {
			output.append("Altura máxima: ");
			output.append(maxHeight);
			output.append("m\n");
		}

		output.append("Requiere ");
		output.append(assistants);
		output.append(" ayudantes y ");
		output.append(responsibles);
		output.append(" responsable");
		output.append(responsibles != 1 ? "s." : ".");

		return output.toString();
	}

	/**
	 * An example of a method - replace this comment with your own
	 *
	 * @param y
	 *            a sample parameter for a method
	 * @return the sum of x and y
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof RideType)) {
			return false;
		}
		return ((RideType) o).getType().equals(type);
	}
}
