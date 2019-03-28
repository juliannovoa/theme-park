package es.uned.poo.themepark;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Write a description of class UserInterface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UserInterface {
	private final RideManager rideManager;
	private final Scanner input;
	private boolean running;
	private final PrintStream writer;

	/**
	 * Constructor for objects of class UserInterface
	 */
	public UserInterface(RideManager manager) {
		rideManager = manager;
		input = new Scanner(System.in);
		running = true;
		writer = System.out;
	}

	/**
	 * An example of a method - replace this comment with your own
	 * 
	 * @param y
	 *            a sample parameter for a method
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

	private void printOptions() {
		writer.println("===========================================");
		writer.println("     Sistema de gestión de atracciones");
		writer.println("===========================================");
		writer.println("  Seleccione operación:");
		writer.println("  L: Mostrar listado de atracciones");
		writer.println("  S: Salir del programa");
	}

	private String getOption() {
		return input.nextLine().toUpperCase();
	}

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

	private void showRideList() {
		writer.println(rideManager.getRidesDescription());
	}

	private void end() {
		writer.println("Saliendo del programa. ¡Hasta luego!");
		running = false;
	}

	private void unknownOption(String option) {
		writer.println("Operación " + option + " no existe.");
	}
}
