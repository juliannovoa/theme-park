package es.uned.poo.themepark;

/**
 * Write a description of class ThemePark here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ThemePark {

	/**
	 * An example of a method - replace this comment with your own
	 * 
	 * @param y
	 *            a sample parameter for a method
	 * @return the sum of x and y
	 */
	public static void main(String args[]) {
		// put your code here
		final RideManager rideManager = RideManager.getInstance();
		final UserInterface ui = new UserInterface(rideManager);

		ui.run();

	}
}
