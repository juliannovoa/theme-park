package es.uned.poo.themepark;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class RateManager.
 */
public class RateManager {

	/** The instance. */
	private static RateManager instance;

	/** The seasons. */
	private final Map<Integer, Season> seasons;

	/** The seasons dates. */
	private final Map<LocalDate, Season> seasonsDates;

	/** The base rates. */
	private final Map<Integer, BigDecimal> baseRates;

	/** The bank holidays. */
	private final Set<LocalDate> bankHolidays;

	/** The working day discount. */
	private BigDecimal workingDayDiscount;

	/** The max discount. */
	private BigDecimal maxDiscount;

	/** The baby discount. */
	private BigDecimal babyDiscount;

	/** The child discount. */
	private BigDecimal childDiscount;

	/** The senior discount. */
	private BigDecimal seniorDiscount;

	/** The disabled discount. */
	private BigDecimal disabledDiscount;

	/** The unemployed discount. */
	private BigDecimal unemployedDiscount;

	/** The business day discount. */
	private BigDecimal businessDayDiscount;

	/** The student discount. */
	private BigDecimal studentDiscount;

	/** The vip rate. */
	private BigDecimal vipRate;

	/**
	 * Instantiates a new rate manager.
	 */
	private RateManager() {
		seasons = new HashMap<>();
		seasonsDates = new HashMap<>();
		baseRates = new HashMap<>();
		bankHolidays = new HashSet<>();
		workingDayDiscount = new BigDecimal(0);
		maxDiscount = new BigDecimal(0);
		babyDiscount = new BigDecimal(0);
		childDiscount = new BigDecimal(0);
		seniorDiscount = new BigDecimal(0);
		disabledDiscount = new BigDecimal(0);
		unemployedDiscount = new BigDecimal(0);
		businessDayDiscount = new BigDecimal(0);
		vipRate = null;

	}

	/**
	 * Gets the working day discount.
	 *
	 * @return the working day discount
	 */
	public BigDecimal getWorkingDayDiscount() {
		return workingDayDiscount;
	}

	/**
	 * Sets the working day discount.
	 *
	 * @param workingDayDiscount
	 *            the new working day discount
	 */
	public void setWorkingDayDiscount(BigDecimal workingDayDiscount) {
		this.workingDayDiscount = workingDayDiscount;
	}

	/**
	 * Gets the max discount.
	 *
	 * @return the max discount
	 */
	public BigDecimal getMaxDiscount() {
		return maxDiscount;
	}

	/**
	 * Sets the max discount.
	 *
	 * @param maxDiscount
	 *            the new max discount
	 */
	public void setMaxDiscount(BigDecimal maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	/**
	 * Gets the baby discount.
	 *
	 * @return the baby discount
	 */
	public BigDecimal getBabyDiscount() {
		return babyDiscount;
	}

	/**
	 * Sets the baby discount.
	 *
	 * @param babyDiscount
	 *            the new baby discount
	 */
	public void setBabyDiscount(BigDecimal babyDiscount) {
		this.babyDiscount = babyDiscount;
	}

	/**
	 * Gets the child discount.
	 *
	 * @return the child discount
	 */
	public BigDecimal getChildDiscount() {
		return childDiscount;
	}

	/**
	 * Sets the child discount.
	 *
	 * @param chilDiscount
	 *            the new child discount
	 */
	public void setChildDiscount(BigDecimal chilDiscount) {
		this.childDiscount = chilDiscount;
	}

	/**
	 * Gets the senior discount.
	 *
	 * @return the senior discount
	 */
	public BigDecimal getSeniorDiscount() {
		return seniorDiscount;
	}

	/**
	 * Sets the senior discount.
	 *
	 * @param retiredDiscount
	 *            the new senior discount
	 */
	public void setSeniorDiscount(BigDecimal retiredDiscount) {
		this.seniorDiscount = retiredDiscount;
	}

	/**
	 * Gets the disabled discount.
	 *
	 * @return the disabled discount
	 */
	public BigDecimal getDisabledDiscount() {
		return disabledDiscount;
	}

	/**
	 * Sets the disabled discount.
	 *
	 * @param disabledDiscount
	 *            the new disabled discount
	 */
	public void setDisabledDiscount(BigDecimal disabledDiscount) {
		this.disabledDiscount = disabledDiscount;
	}

	/**
	 * Gets the unemployed discount.
	 *
	 * @return the unemployed discount
	 */
	public BigDecimal getUnemployedDiscount() {
		return unemployedDiscount;
	}

	/**
	 * Sets the unemployed discount.
	 *
	 * @param unemployedDiscount
	 *            the new unemployed discount
	 */
	public void setUnemployedDiscount(BigDecimal unemployedDiscount) {
		this.unemployedDiscount = unemployedDiscount;
	}

	/**
	 * Gets the business day discount.
	 *
	 * @return the business day discount
	 */
	public BigDecimal getBusinessDayDiscount() {
		return businessDayDiscount;
	}

	/**
	 * Sets the business day discount.
	 *
	 * @param businessDayDiscount
	 *            the new business day discount
	 */
	public void setBusinessDayDiscount(BigDecimal businessDayDiscount) {
		this.businessDayDiscount = businessDayDiscount;
	}

	/**
	 * Gets the student discount.
	 *
	 * @return the student discount
	 */
	public BigDecimal getStudentDiscount() {
		return studentDiscount;
	}

	/**
	 * Sets the student discount.
	 *
	 * @param studentDiscount
	 *            the new student discount
	 */
	public void setStudentDiscount(BigDecimal studentDiscount) {
		this.studentDiscount = studentDiscount;
	}

	/**
	 * Gets the vip rate.
	 *
	 * @return the vip rate
	 */
	public BigDecimal getVipRate() {
		return vipRate;
	}

	/**
	 * Sets the vip rate.
	 *
	 * @param vipRate
	 *            the new vip rate
	 */
	public void setVipRate(BigDecimal vipRate) {
		this.vipRate = vipRate;
	}

	/**
	 * Gets the single instance of RateManager.
	 *
	 * @return single instance of RateManager
	 */
	public static RateManager getInstance() {
		if (instance == null) {
			instance = new RateManager();
		}
		return instance;
	}

	/**
	 * Adds the season.
	 *
	 * @param season
	 *            the season
	 */
	public void addSeason(Season season) {
		if (!seasons.containsKey(season.getId())) {
			seasons.put(season.getId(), season);
		} else {
			throw new IllegalArgumentException("Season already exists");
		}
	}

	/**
	 * Gets the season.
	 *
	 * @param seasonId
	 *            the season id
	 * @return the season
	 */
	public Season getSeasonById(int seasonId) {
		final Season season = seasons.get(seasonId);
		if (season == null) {
			throw new IllegalArgumentException("This season does not exists.");
		}
		return season;
	}

	/**
	 * Gets the season by date.
	 *
	 * @param date
	 *            the date
	 * @return the season by date
	 */
	public Season getSeasonByDate(LocalDate date) {
		final Season season = seasonsDates.get(date);
		if (season == null) {
			throw new IllegalArgumentException("There is not a season assigned to this date.");
		}
		return season;
	}

	/**
	 * Adds the dates to season.
	 *
	 * @param seasonId
	 *            the season id
	 * @param newPeriod
	 *            the new period
	 */
	public void addDatesToSeason(int seasonId, TimePeriod newPeriod) {
		final LocalDate end = newPeriod.getEnd();
		final Season season = getSeasonById(seasonId);
		for (LocalDate date = newPeriod.getStart(); !date.isAfter(end); date = date.plusDays(1)) {
			seasonsDates.put(date, season);
		}
	}

	/**
	 * Adds the base rates.
	 *
	 * @param year
	 *            the year
	 * @param rate
	 *            the rate
	 */
	public void addBaseRates(Integer year, BigDecimal rate) {
		baseRates.put(year, rate);
	}

	/**
	 * Gets the ticket basic rate.
	 *
	 * @param season
	 *            the season
	 * @param year
	 *            the year
	 * @return the ticket basic rate
	 */
	public BigDecimal getTicketBasicRate(Season season, int year) {
		final BigDecimal baseRate = baseRates.get(year);
		if (baseRate == null) {
			throw new IllegalArgumentException("There is no base rate assigned to this date.");
		}
		return baseRate.multiply(season.getRate());
	}

	/**
	 * Gets the ticket basic rate.
	 *
	 * @param date
	 *            the date
	 * @return the ticket basic rate
	 */
	public BigDecimal getTicketBasicRate(LocalDate date) {
		final Season season = seasons.get(date);
		if (season == null) {
			throw new IllegalArgumentException("There is no season assigned to this date.");
		}
		return getTicketBasicRate(season, date.getYear());
	}

	/**
	 * Adds the holiday.
	 *
	 * @param date
	 *            the date
	 */
	public void addHoliday(LocalDate date) {
		bankHolidays.add(date);
	}

	/**
	 * Checks if is business day.
	 *
	 * @param date
	 *            the date
	 * @return true, if is business day
	 */
	public boolean isBusinessDay(LocalDate date) {
		return date.getDayOfWeek().getValue() < 6 && !bankHolidays.contains(date);
	}

	/**
	 * Initialise sample.
	 */
	public void initialiseSample() {

		baseRates.put(2019, new BigDecimal(60));

		workingDayDiscount = BigDecimal.valueOf(0.2);
		maxDiscount = BigDecimal.valueOf(0.9);
		babyDiscount = BigDecimal.valueOf(1);
		childDiscount = BigDecimal.valueOf(0.5);
		seniorDiscount = BigDecimal.valueOf(0.35);
		disabledDiscount = BigDecimal.valueOf(0.2);
		unemployedDiscount = BigDecimal.valueOf(0.2);
		studentDiscount = BigDecimal.valueOf(0.2);
		businessDayDiscount = BigDecimal.valueOf(0.2);
		vipRate = BigDecimal.valueOf(50);

		addSeason(new Season(1, "Temporada Baja", new BigDecimal(0.85)));
		addSeason(new Season(2, "Temporada Media", new BigDecimal(1)));
		addSeason(new Season(3, "Temporada Alta", new BigDecimal(1.15)));

		addDatesToSeason(3, new TimePeriod(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 8)));
		addDatesToSeason(2, new TimePeriod(LocalDate.of(2019, 1, 9), LocalDate.of(2019, 1, 31)));
		addDatesToSeason(1, new TimePeriod(LocalDate.of(2019, 2, 1), LocalDate.of(2019, 2, 28)));
		addDatesToSeason(2, new TimePeriod(LocalDate.of(2019, 3, 1), LocalDate.of(2019, 3, 31)));
		addDatesToSeason(3, new TimePeriod(LocalDate.of(2019, 4, 1), LocalDate.of(2019, 4, 30)));
		addDatesToSeason(2, new TimePeriod(LocalDate.of(2019, 5, 1), LocalDate.of(2019, 31, 7)));
		addDatesToSeason(3, new TimePeriod(LocalDate.of(2019, 8, 1), LocalDate.of(2019, 8, 31)));
		addDatesToSeason(2, new TimePeriod(LocalDate.of(2019, 9, 1), LocalDate.of(2019, 31, 10)));
		addDatesToSeason(1, new TimePeriod(LocalDate.of(2019, 11, 1), LocalDate.of(2019, 11, 31)));
		addDatesToSeason(3, new TimePeriod(LocalDate.of(2019, 12, 1), LocalDate.of(2019, 12, 31)));

		addHoliday(LocalDate.of(1, 1, 2019));
		addHoliday(LocalDate.of(5, 3, 2019));
		addHoliday(LocalDate.of(19, 3, 2019));
		addHoliday(LocalDate.of(1, 1, 2019));
		addHoliday(LocalDate.of(18, 4, 2019));
		addHoliday(LocalDate.of(19, 4, 2019));
		addHoliday(LocalDate.of(1, 5, 2019));
		addHoliday(LocalDate.of(17, 5, 2019));
		addHoliday(LocalDate.of(24, 6, 2019));
		addHoliday(LocalDate.of(25, 7, 2019));
		addHoliday(LocalDate.of(15, 8, 2019));
		addHoliday(LocalDate.of(12, 10, 2019));
		addHoliday(LocalDate.of(1, 11, 2019));
		addHoliday(LocalDate.of(6, 12, 2019));
		addHoliday(LocalDate.of(25, 12, 2019));

	}

}
