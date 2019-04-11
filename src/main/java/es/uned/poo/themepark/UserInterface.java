package es.uned.poo.themepark;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * The Class UserInterface.
 */
public class UserInterface {

	/** The ride manager. */
	private final RideManager rideManager;

	/** The input. */
	private final Scanner input;

	/** The running. */
	private boolean running;

	/** The writer. */
	private final PrintStream writer;

	/**
	 * Constructor for objects of class UserInterface.
	 *
	 * @param manager
	 *            the manager
	 */
	public UserInterface(RideManager manager) {
		rideManager = manager;
		input = new Scanner(System.in);
		running = true;
		writer = System.out;
	}

	/**
	 * An example of a method - replace this comment with your own.
	 *
	 * @return the sum of x and y
	 */
	public void run() {
		String option;
		while (running) {
			printOptions();
			option = getOption();
			executeOption(option);
		}
	}

	/**
	 * Prints the options.
	 */
	private void printOptions() {
		writer.println("===========================================");
		writer.println("     Sistema de gestión de atracciones");
		writer.println("===========================================");
		writer.println("  Seleccione operación:");
		writer.println("  L: Mostrar listado de atracciones");
		writer.println("  S: Salir del programa");
	}

	/**
	 * Gets the option.
	 *
	 * @return the option
	 */
	private String getOption() {
		return input.nextLine().toUpperCase();
	}

	/**
	 * Execute option.
	 *
	 * @param option
	 *            the option
	 */
	private void executeOption(String option) {
		switch (option) {
		case "L":
			showRideList();
			break;
		case "S":
			end();
			break;
		default:
			unknownOption(option);
			break;
		}
	}

	/**
	 * Show ride list.
	 */
	private void showRideList() {
		writer.println(rideManager.getRidesDescription());
	}

	/**
	 * End.
	 */
	private void end() {
		writer.println("Saliendo del programa. ¡Hasta luego!");
		running = false;
	}

	/**
	 * Unknown option.
	 *
	 * @param option
	 *            the option
	 */
	private void unknownOption(String option) {
		writer.println("Operación " + option + " no existe.");
	}
}
