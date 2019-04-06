package es.uned.poo.themepark;

public class WorkforceParameters {
	private final int basicSalary;
	private final double responsibleBonus;
	private final double customerServiceBonus;
	private final double publicRelationshipBonus;
	private final double customerServiceRatio;
	private final double publicRelationshipRatio;

	public WorkforceParameters(int basicSalary, double responsibleBonus, double customerServiceBonus,
			double publicRelationshipBonus, double customerServiceRatio, double publicRelationshipRatio) {

		this.basicSalary = basicSalary;
		this.responsibleBonus = responsibleBonus;
		this.customerServiceBonus = customerServiceBonus;
		this.publicRelationshipBonus = publicRelationshipBonus;
		this.customerServiceRatio = customerServiceRatio;
		this.publicRelationshipRatio = publicRelationshipRatio;
	}

	public int getBasicSalary() {
		return basicSalary;
	}

	public double getResponsibleBonus() {
		return responsibleBonus;
	}

	public double getCustomerServiceBonus() {
		return customerServiceBonus;
	}

	public double getPublicRelationshipBonus() {
		return publicRelationshipBonus;
	}

	public double getCustomerServiceRatio() {
		return customerServiceRatio;
	}

	public double getPublicRelationshipRatio() {
		return publicRelationshipRatio;
	}

}
