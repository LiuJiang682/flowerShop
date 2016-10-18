package co.cogent.flowershop.userenter.fixture;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class FlowerShopTestFixture {

	public static InputStream givenByteArrayInputStream(String input) {
		InputStream in = new ByteArrayInputStream(input.getBytes());
		return in;
	}

}
