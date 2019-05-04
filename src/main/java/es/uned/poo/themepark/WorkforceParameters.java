package es.uned.poo.themepark;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The Class WorkforceParameters.
 */
public class WorkforceParameters {

	/** The base salary. */
	private final BigDecimal baseDailySalary;

	/** The responsible bonus. */
	private final BigDecimal responsibleBonus;

	/** The customer service bonus. */
	private final BigDecimal customerServiceBonus;

	/** The public relationship bonus. */
	private final BigDecimal publicRelationshipBonus;

	/** The customer service ratio. */
	private final BigDecimal customerServiceRatio;

	/** The public relationship ratio. */
	private final BigDecimal publicRelationshipRatio;

	/**
	 * Instantiates a new workforce parameters.
	 *
	 * @param baseSalary
	 *            the basic salary
	 * @param responsibleBonus
	 *            the responsible bonus
	 * @param customerServiceBonus
	 *            the customer service bonus
	 * @param publicRelationshipBonus
	 *            the public relationship bonus
	 * @param customerServiceRatio
	 *            the customer service ratio
	 * @param publicRelationshipRatio
	 *            the public relationship ratio
	 */
	public WorkforceParameters(BigDecimal baseSalary, BigDecimal responsibleBonus, BigDecimal customerServiceBonus,
			BigDecimal publicRelationshipBonus, BigDecimal customerServiceRatio, BigDecimal publicRelationshipRatio) {

		this.baseDailySalary = baseSalary.multiply(BigDecimal.valueOf(12)).divide(BigDecimal.valueOf(365),
				RoundingMode.CEILING);
		this.responsibleBonus = responsibleBonus;
		this.customerServiceBonus = customerServiceBonus;
		this.publicRelationshipBonus = publicRelationshipBonus;
		this.customerServiceRatio = customerServiceRatio;
		this.publicRelationshipRatio = publicRelationshipRatio;
	}

	/**
	 * Calculate daily cost.
	 *
	 * @param responsibles
	 *            the responsibles
	 * @param assistants
	 *            the assistants
	 * @return the big decimal
	 */
	public BigDecimal calculateDailyCost(int responsibles, int assistants) {
		BigDecimal cost;

		final BigDecimal rideStaff = BigDecimal.valueOf(responsibles + assistants);
		final BigDecimal customerServiceStaff = customerServiceRatio.multiply(rideStaff).setScale(0,
				BigDecimal.ROUND_CEILING);
		final BigDecimal publicRelationShipStaff = publicRelationshipRatio.multiply(rideStaff).setScale(0,
				BigDecimal.ROUND_CEILING);

		cost = baseDailySalary.multiply(responsibleBonus).multiply(BigDecimal.valueOf(responsibles));
		cost = cost.add(BigDecimal.valueOf(assistants).multiply(baseDailySalary));
		cost = cost.add(customerServiceStaff.multiply(baseDailySalary).multiply(customerServiceBonus));
		cost = cost.add(publicRelationShipStaff.multiply(baseDailySalary).multiply(publicRelationshipBonus));

		return cost;

	}

}
