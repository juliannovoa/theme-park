package es.uned.poo.themepark;

/**
 * The Class RideType.
 */
public class RideType {

	/** The type. */
	private final String type;

	/** The assistants. */
	private final int assistants;

	/** The responsibles. */
	private final int responsibles;

	/** The children use. */
	private boolean childrenUse;

	/** The adult use. */
	private boolean adultUse;

	/** The vip. */
	private boolean vip;

	/** The max height. */
	private Double maxHeight;

	/** The min height. */
	private Double minHeight;

	/**
	 * Constructor for objects of class Ride.
	 *
	 * @param type
	 *            the type
	 * @param assistants
	 *            the assistants
	 * @param responsibles
	 *            the responsibles
	 */
	public RideType(String type, int assistants, int responsibles) {
		this.type = type;
		this.assistants = assistants;
		this.responsibles = responsibles;
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @return the number of assistants assigned to a ride
	 */
	public RideType withAdultUse() {
		adultUse = true;
		return this;
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @return the number of assistants assigned to a ride
	 */
	public RideType withChildrenUse() {
		childrenUse = true;
		return this;
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @param height
	 *            the height
	 * @return the number of assistants assigned to a ride
	 */
	public RideType withMaxHeight(double height) {
		maxHeight = height;
		return this;
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @param height
	 *            the height
	 * @return the number of assistants assigned to a ride
	 */
	public RideType withMinHeight(double height) {
		minHeight = height;
		return this;
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @return the number of assistants assigned to a ride
	 */
	public RideType withVipUse() {
		vip = true;
		return this;
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @return the number of assistants assigned to a ride
	 */
	public String getType() {
		return type;
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @return the number of assistants assigned to a ride
	 */
	public int getAssitants() {
		return assistants;
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @return the sum of x and y
	 */
	public int getResponsibles() {
		return responsibles;
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
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
	 * An example of a method - replace this comment with your own.
	 *
	 * @param o
	 *            the o
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
