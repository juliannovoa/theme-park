package es.uned.poo.themepark;

import java.math.BigDecimal;

/**
 * The Class WorkforceParameters.
 */
public class WorkforceParameters {

	/** The base salary. */
	private final BigDecimal baseSalary;

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
	 * @param basicSalary
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
	public WorkforceParameters(BigDecimal basicSalary, BigDecimal responsibleBonus, BigDecimal customerServiceBonus,
			BigDecimal publicRelationshipBonus, BigDecimal customerServiceRatio, BigDecimal publicRelationshipRatio) {

		this.baseSalary = basicSalary;
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
		final BigDecimal rideStaff = BigDecimal.valueOf(responsibles + assistants);
		final BigDecimal customerServiceStaff = customerServiceRatio.multiply(rideStaff).setScale(0,
				BigDecimal.ROUND_CEILING);
		final BigDecimal publicRelationShipStaff = publicRelationshipRatio.multiply(rideStaff).setScale(0,
				BigDecimal.ROUND_CEILING);

		final BigDecimal cost = baseSalary.multiply(responsibleBonus).multiply(BigDecimal.valueOf(responsibles));
		cost.add(BigDecimal.valueOf(assistants).multiply(baseSalary));
		cost.add(customerServiceStaff.multiply(baseSalary).multiply(customerServiceBonus));
		cost.add(publicRelationShipStaff.multiply(baseSalary).multiply(publicRelationshipBonus));

		return cost;

	}

}
