package co.cogent.flowershop.userenter.model;

import java.io.Serializable;
import java.math.BigDecimal;

import co.cogent.flowershop.userenter.UserEntry;

public class DefaultUserEntry implements UserEntry, Serializable {

	/**
	 * Generated Serial Version UID.
	 */
	private static final long serialVersionUID = 6460752020072916957L;
	
	private BigDecimal totalNumber;
	private String code;
	
	public DefaultUserEntry(BigDecimal totalNumber, String code) {
		this.totalNumber = totalNumber;
		this.code = code;
	}

	public BigDecimal getTotalNumber() {
		return this.totalNumber;
	}

	public String getCode() {
		return code;
	}

}
