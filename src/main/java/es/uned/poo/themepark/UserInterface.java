package es.uned.poo.themepark;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * The Class UserInterface.
 */
public class UserInterface implements Runnable {

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
	@Override
	public void run() {
		String option;
		while (running) {
			printOptions();
			option = getOption();
			writer.println();
			executeOption(option);
			writer.print("\n\n");
		}
	}

	/**
	 * Prints the options.
	 */
	private void printOptions() {
		writer.println("===============================================");
		writer.println("       Sistema de gestión de atracciones");
		writer.println("===============================================");
		writer.println("  Listado de operaciones:");
		writer.println("  1: Mostrar informe anual de gastos");
		writer.println("  2: Mostrar listado de atracciones");
		writer.println("  3: Añadir periodo de actividad a atracción");
		writer.println("  S: Salir del programa");
		writer.print("\nSeleccione operación: ");
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
		case "1":
			showCostReport();
			break;
		case "2":
			showRideList();
			break;
		case "3":
			addWorkingPeriod();
			break;
		case "S":
			end();
			break;
		default:
			unknownOption(option);
			break;
		}
	}

	private void addWorkingPeriod() {
		String rideID;
		LocalDate start, end;

		try {
			writer.print("Indique atracción: ");
			rideID = input.nextLine().trim().toUpperCase();
			writer.println("Indique fecha de inicio: ");
			start = parseDate();
			writer.println("Indique fecha de fin: ");
			end = parseDate();

			rideManager.addWorkingPeriod(rideID, start, end);

		} catch (final Exception e) {
			writer.println("Error al añadir la fecha de actividad: " + e.getMessage());
		}

	}

	private LocalDate parseDate() {
		int day, month, year;
		writer.print("Día: ");
		day = Integer.parseInt(input.nextLine().trim());
		writer.print("Mes: ");
		month = Integer.parseInt(input.nextLine().trim());
		writer.print("Año: ");
		year = Integer.parseInt(input.nextLine().trim());

		return LocalDate.of(year, month, day);

	}

	private void showCostReport() {
		writer.print("Seleccione año: ");
		try {
			writer.println(rideManager.generateReportByYear(Integer.parseInt(input.nextLine().trim())));
		} catch (final NumberFormatException e) {
			writer.println("El año introducido no tiene el formato correcto: " + e.getMessage());
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
