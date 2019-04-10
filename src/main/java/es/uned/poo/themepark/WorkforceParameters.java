package es.uned.poo.themepark;

import java.math.BigDecimal;

public class WorkforceParameters {
	private final BigDecimal baseSalary;
	private final BigDecimal responsibleBonus;
	private final BigDecimal customerServiceBonus;
	private final BigDecimal publicRelationshipBonus;
	private final BigDecimal customerServiceRatio;
	private final BigDecimal publicRelationshipRatio;

	public WorkforceParameters(BigDecimal basicSalary, BigDecimal responsibleBonus, BigDecimal customerServiceBonus,
			BigDecimal publicRelationshipBonus, BigDecimal customerServiceRatio, BigDecimal publicRelationshipRatio) {

		this.baseSalary = basicSalary;
		this.responsibleBonus = responsibleBonus;
		this.customerServiceBonus = customerServiceBonus;
		this.publicRelationshipBonus = publicRelationshipBonus;
		this.customerServiceRatio = customerServiceRatio;
		this.publicRelationshipRatio = publicRelationshipRatio;
	}

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
