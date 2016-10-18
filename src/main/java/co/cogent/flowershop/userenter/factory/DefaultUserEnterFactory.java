package co.cogent.flowershop.userenter.factory;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import co.cogent.flowershop.userenter.UserEntry;
import co.cogent.flowershop.userenter.model.DefaultUserEntry;

public class DefaultUserEnterFactory implements UserEnter {

	private static final Logger LOGGER = Logger.getLogger(DefaultUserEnterFactory.class);
	
	private static final String CTRL_C = "\u0003";
	
	private static final int TWO = 2;
	
	private static final int ONE = 1;

	private static final int ZERO = 0;

	private Scanner scanner;
	
	public DefaultUserEnterFactory() {
		this(System.in);
	}
	
	public DefaultUserEnterFactory(InputStream in) {
		this.scanner = new Scanner(in);
	}
	
	public Optional<UserEntry> constructUserEntry(String[] userEntered) {
		Optional<UserEntry> userEntried = Optional.empty();
		try {
			BigDecimal totalNumber = new BigDecimal(userEntered[ZERO]);
			DefaultUserEntry userEntry = new DefaultUserEntry(totalNumber, userEntered[ONE]);
			userEntried = Optional.of(userEntry);
		}
		catch (NumberFormatException e) {
			//Ignore the exception.
		}
		
		return userEntried;
	}

	@Override
	public Optional<UserEntry> getUserInput() {
		Optional<UserEntry> userEntred = Optional.empty();
		// User interactive mode
		String userEntered = scanner.nextLine();
		if (CTRL_C.equalsIgnoreCase(userEntered))
			throw new NoSuchElementException("No line found");
		
		if (StringUtils.isNoneBlank(userEntered)) {
			String[] strings = userEntered.split(UserEnter.SPACE);
			if (TWO == strings.length) {
				Optional<UserEntry> userEntry = this.constructUserEntry(strings);
				if (userEntry.isPresent()) {
					userEntred = Optional.of(userEntry.get());
				}
			}
		}
		return userEntred;
	}

	public Scanner getScanner() {
		return this.scanner;
	}

}
