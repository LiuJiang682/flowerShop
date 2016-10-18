package co.cogent.flowershop.price.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * In order to perform the cost and bundle break down calculation 
 * As the flower shop manager
 * I like to able to separate the price with units
 */
public class PriceTest {

	/**
	 * Given the correct unit and price
	 * When the constructor called
	 * Then the object should return
	 */
	@Test
	public void whenCorrectUnitsAndPriceProvidedThenObjectShouldReturn() {
		//Given the correct units and price
		Integer units = 10;
		BigDecimal price = new BigDecimal("6.80");
		//When the constructor called
		Price priceObject = new Price(units, price);
		//Then object should return
		assertNotNull(priceObject);
		assertEquals(units, priceObject.getUnits());
		assertEquals(price, priceObject.getPrice());
	}
	
	/**
	 * Given a null units
	 * When the constructor called
	 * Then an IllegalArgumentException raised
	 */
	@Test
	public void whenNullUnitsProvidedThenExceptionRaised() {
		//Given the null units
		Integer units = null;
		BigDecimal price = new BigDecimal("6.80");
		//When the constructor called
		try {
			new Price(units, price);
			fail("Program reached unexpected point!");
		}
		catch (IllegalArgumentException e) {
			//Then the exception should raised
			String msg = e.getMessage();
			assertNotNull(msg);
			assertEquals("units cannot be null!", msg);
		}
	}
	
	/**
	 * Given a null price
	 * When the constructor called
	 * Then an IllegalArgumentException raised
	 */
	@Test
	public void whenNullPriceProvidedThenExceptionRaised() {
		//Given the null price
		Integer units = 10;
		BigDecimal price = null;
		//When the constructor called
		try {
			new Price(units, price);
			fail("Program reached unexpected point!");
		}
		catch (IllegalArgumentException e) {
			//Then the exception should raised
			String msg = e.getMessage();
			assertNotNull(msg);
			assertEquals("price cannot be null!", msg);
		}
	}
}
