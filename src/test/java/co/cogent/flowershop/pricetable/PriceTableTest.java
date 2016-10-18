package co.cogent.flowershop.pricetable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import co.cogent.flowershop.price.model.Price;

/**
 * In order to perform the cost and bundle break down calculation 
 * As the flower shop manager
 * I like to able to look up the price
 */
public class PriceTableTest {

	/**
	 * Given a valid code
	 * When the lookup method called
	 * Then the correct price list should return
	 */
	@Test
	public void whenValidCodeProvidedThenPriceListShouldReturn() {
		//Given a valid code
		String code = "R12";
		//When the lookup method called
		Optional<List<Price>> priceList = PriceTable.lookup(code);
		//Then the correct price list should return
		assertNotNull(priceList);
		assertTrue(priceList.isPresent());
		assertTrue(2 == priceList.get().size());
		Price price1 = priceList.get().get(0);
		assertEquals(new Integer(10), price1.getUnits());
		assertEquals(new BigDecimal("12.99"), price1.getPrice());
		Price price2 = priceList.get().get(1);
		assertEquals(new Integer(5), price2.getUnits());
		assertEquals(new BigDecimal("6.99"), price2.getPrice());
	}
}
