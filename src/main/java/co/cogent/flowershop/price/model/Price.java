package co.cogent.flowershop.price.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Price implements Serializable {

	/**
	 * Generated Serial Version UID.
	 */
	private static final long serialVersionUID = -4557884722479503216L;
	
	private Integer units;
	private BigDecimal price;
	
	public Price(final Integer units, final BigDecimal price) {
		if (null == units) {
			throw new IllegalArgumentException("units cannot be null!");
		}
		if (null == price) {
			throw new IllegalArgumentException("price cannot be null!");
		}
		this.units = units;
		this.price = price;
	}

	public Integer getUnits() {
		return units;
	}

	public BigDecimal getPrice() {
		return price;
	}
}
