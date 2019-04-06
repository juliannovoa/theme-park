package es.uned.poo.themepark;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// TODO: Auto-generated Javadoc
/**
 * The Class TimePeriod.
 */
public class TimePeriod implements Comparable<TimePeriod> {

	/** The start. */
	private LocalDate start;

	/** The end. */
	private LocalDate end;

	/**
	 * Instantiates a new time period.
	 *
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 */
	public TimePeriod(LocalDate start, LocalDate end) {
		if (start.isBefore(end)) {
			throw new IllegalArgumentException("Beginning date must be before the end date");
		}
		this.start = start;
		this.end = end;
	}

	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public LocalDate getStart() {
		return start;
	}

	/**
	 * Gets the end.
	 *
	 * @return the end
	 */
	public LocalDate getEnd() {
		return end;
	}

	/**
	 * Overlap.
	 *
	 * @param other
	 *            the other
	 * @return true, if successful
	 */
	public boolean overlap(TimePeriod other) {
		if (start.isAfter(other.getStart())) {
			// Other begins first
			return other.getEnd().isAfter(start);
		} else {
			// This beings first
			return other.getStart().isBefore(end);
		}
	}

	/**
	 * Extend.
	 *
	 * @param other
	 *            the other
	 * @return true, if successful
	 */
	public boolean extendPeriod(TimePeriod other) {

		if (!overlap(other)) {
			return false;
		}

		final LocalDate otherBeginning = other.getStart();
		final LocalDate otherEnd = other.getEnd();

		if (start.isAfter(otherBeginning)) {
			start = otherBeginning;
		}

		if (end.isAfter(otherEnd)) {
			end = otherEnd;
		}

		return true;
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public long getLength() {
		return ChronoUnit.DAYS.between(start, end);
	}

	/**
	 * Checks if is after.
	 *
	 * @param other
	 *            the other
	 * @return true, if is after
	 */
	public boolean isAfter(TimePeriod other) {
		return other.getEnd().isBefore(start);
	}

	/**
	 * In year.
	 *
	 * @param year
	 *            the year
	 * @return true, if successful
	 */
	public boolean inYear(int year) {
		return (start.getYear() <= year) && (end.getYear() >= year);
	}

	public boolean afterYear(int year) {
		return start.getYear() > year;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(TimePeriod other) {
		return start.compareTo(other.getStart());
	}

}
