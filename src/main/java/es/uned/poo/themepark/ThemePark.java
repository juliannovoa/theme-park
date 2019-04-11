package es.uned.poo.themepark;

/**
 * The Class ThemePark.
 */
public class ThemePark {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String args[]) {
		// put your code here
		final RideManager rideManager = RideManager.getInstance();
		final UserInterface ui = new UserInterface(rideManager);

		ui.run();

	}
}
