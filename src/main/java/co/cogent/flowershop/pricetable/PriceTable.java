package co.cogent.flowershop.pricetable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import co.cogent.flowershop.price.model.Price;

/**
 * This class provided the price break down 
 * for all flowers sells at the shop. 
 * 
 * It is fine for the current product range, it
 * may needs to refactor to accommodate future
 * products expansion or constant price change. 
 *
 */
public class PriceTable {

	private static Map<String, List<Price>> priceTable;
	
	public static Optional<List<Price>> lookup(String code) {
		Optional<List<Price>> pricesList = Optional.empty();
		if (priceTable.containsKey(code)) {
			List<Price> prices = priceTable.get(code);
			pricesList = Optional.of(prices);
		}
		return pricesList;
	}

	protected static List<Price> getRosesPriceList() {
		List<Price> roses = new ArrayList<>();
		Price five = new Price(5, new BigDecimal("6.99"));
		Price ten = new Price(10, new BigDecimal("12.99"));
		roses.add(ten);
		roses.add(five);
		return roses;
	}
	
	protected static List<Price> getLiliesPriceList() {
		List<Price> lilies = new ArrayList<>();
		Price three = new Price(3, new BigDecimal("9.95"));
		Price six = new Price(6, new BigDecimal("16.95"));
		Price nine = new Price(9, new BigDecimal("24.95"));
		lilies.add(nine);
		lilies.add(six);
		lilies.add(three);
		return lilies;
	}
	
	protected static List<Price> getTulipsPriceList() {
		List<Price> tulips = new ArrayList<>();
		Price three = new Price(3, new BigDecimal("5.95"));
		Price six = new Price(6, new BigDecimal("9.95"));
		Price nine = new Price(9, new BigDecimal("16.95"));
		tulips.add(nine);
		tulips.add(six);
		tulips.add(three);
		return tulips;
	}
	
	static {
		HashMap<String, List<Price>> table = new HashMap<>();
		table.put("R12", getRosesPriceList());
		table.put("L09", getLiliesPriceList());
		table.put("T58", getTulipsPriceList());
		priceTable = Collections.unmodifiableMap(table);
	}
}
