package es.uned.poo.themepark;

import java.math.BigDecimal;

public class Season {

	private final int id;
	private final String description;
	private final BigDecimal rate;

	public Season(int id, String description, BigDecimal rate) {
		this.id = id;
		this.description = description;
		this.rate = rate;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Season) {
			return id == ((Season) o).getId();
		}
		return false;
	}

}
