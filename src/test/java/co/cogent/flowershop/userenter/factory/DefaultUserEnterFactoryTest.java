package co.cogent.flowershop.userenter.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

import co.cogent.flowershop.userenter.UserEntry;
import co.cogent.flowershop.userenter.fixture.FlowerShopTestFixture;
import co.cogent.flowershop.userenter.model.DefaultUserEntry;

/**
 * In order to perform the cost and bundle break down calculation 
 * As the flower manager
 * operator, I like to able to accept the user enter
 */
public class DefaultUserEnterFactoryTest {

	private DefaultUserEnterFactory testInstance;
	private Scanner mockScanner;

	@Before
	public void setUp() {
		this.testInstance = new DefaultUserEnterFactory();
	}

	@After
	public void tearDown() {
		this.testInstance = null;
		this.mockScanner = null;
	}

	/**
	 * Given the valid string array
	 * When the constructUserEntry method called
	 * Then an UserEntry Optional object should return
	 */
	@Test
	public void whenValidStringArrayProvidedThenUserEntryObjectShouldReturn() {
		//Given the valid string array
		String[] userEntered = {"10", "R12"};
		//When the constructUserEntry method called
		Optional<UserEntry> userEntry = this.testInstance.constructUserEntry(userEntered);
		//Then a valid UserEntry Optional object should return
		assertNotNull(userEntry);
		assertTrue(userEntry.isPresent());
		DefaultUserEntry defaultUserEntry = (DefaultUserEntry)userEntry.get();
		assertEquals(new BigDecimal(10), defaultUserEntry.getTotalNumber());
		assertEquals("R12", defaultUserEntry.getCode());
	}
	
	/**
	 * Given an invalid string array
	 * When the constructUserEntry method called
	 * Then an empty UserEntry Optional object should return
	 */
	@Test
	public void whenInvalidStringArrayProvidedThenEmptyUserEntryObjectShouldReturn() {
		//Given the valid string array
		String[] userEntered = {"abc", "R12"};
		//When the constructUserEntry method called
		Optional<UserEntry> userEntry = this.testInstance.constructUserEntry(userEntered);
		//Then a valid UserEntry Optional object should return
		assertNotNull(userEntry);
		assertFalse(userEntry.isPresent());
	}
	
	/**
	 * Give user entered a valid string
	 * When the getUserInput method called
	 * Then an UserEntry object should return
	 * @throws Exception -- when unexpected happen.
	 */
	@Test
	public void whenUserEnteredValidStringThenAValidOptionalShouldReturn() throws Exception {
		//Given a valid user entered string
		String userEntered = "10 R12";
		DefaultUserEnterFactory partialMockFactory = givenPartialMockFactory(userEntered);
		UserEntry mockUserEntry = PowerMockito.mock(UserEntry.class);
		PowerMockito.doReturn(Optional.of(mockUserEntry)).when(partialMockFactory, "constructUserEntry", Matchers.any(String[].class));
		assertNotNull(partialMockFactory.getScanner());
		
		//When the getUserInput method called
		Optional<UserEntry> userEntried = partialMockFactory.getUserInput();
		//Then the userEntries is empty
		assertNotNull(userEntried);
		assertTrue(userEntried.isPresent());
		assertEquals(mockUserEntry, userEntried.get());
		
		verify(partialMockFactory).constructUserEntry(Matchers.any(String[].class));
	}
	
	/**
	 * Give user entered an invalid string
	 * When the getUserInput method called
	 * Then empty optional should return
	 * @throws Exception -- when unexpected happen.
	 */
	@Test
	public void whenUserEnteredInvalidStringThenEmptyOptionalShouldReturn() throws Exception {
		//Given a invalid user entered string
		String userEntered = "abc";
		DefaultUserEnterFactory partialMockFactory = givenPartialMockFactory(userEntered);
		
		assertNotNull(partialMockFactory.getScanner());
		
		//When the getUserInput method called
		Optional<UserEntry> userEntries = partialMockFactory.getUserInput();
		//Then the userEntries is empty
		assertNotNull(userEntries);
		assertFalse(userEntries.isPresent());
		
		verify(partialMockFactory, times(0)).constructUserEntry(Matchers.any(String[].class));
	}
	
	/**
	 * Give user entered an empty string
	 * When the getUserInput method called
	 * Then NoSuchElementException will raised
	 * @throws Exception -- when unexpected happen.
	 */
	@Test
	public void whenUserEnteredEmptyStringThenExceptionShouldRaised() throws Exception {
		//Given an empty user entered string
		String userEntered = "";
		DefaultUserEnterFactory partialMockFactory = givenPartialMockFactory(userEntered);
		
		assertNotNull(partialMockFactory.getScanner());
		
		//When the getUserInput method called
		try {
			partialMockFactory.getUserInput();
			fail("Program reached unexpected point!");
		}
		catch (NoSuchElementException e) {
			//Then no line element found
			accessNoLineFoundException(e);
		}
	}
	
	/**
	 * Given the user entered a Ctrl-C
	 * When UserEntryFactory received the input 
	 * Then the userEntryFactory exit
	 * @throws Exception 
	 */
	@Test
	public void whenUserEnteredCtrlCThenExist() throws Exception {
		//Given the DefaultUserEnterFactory and mock scanner 
		String userEntered = "\u0003";
		DefaultUserEnterFactory partialMockFactory = givenPartialMockFactory(userEntered);
		
		assertNotNull(partialMockFactory.getScanner());
		
		//When the getUserInput method called
		try {
			partialMockFactory.getUserInput();
			fail("Program reached unexpected point!");
		}
		catch (NoSuchElementException e) {
			//Then no line element found
			accessNoLineFoundException(e);
		}
		
	}
	

	private DefaultUserEnterFactory givenPartialMockFactory(String userEntered) throws Exception {
		DefaultUserEnterFactory partialMockFactory = PowerMockito.mock(DefaultUserEnterFactory.class);
		givenMockByteArrayInputStreamScanner(userEntered);
		Whitebox.setInternalState(partialMockFactory, "scanner", mockScanner);
		PowerMockito.doCallRealMethod().when(partialMockFactory, "getUserInput");
		PowerMockito.doCallRealMethod().when(partialMockFactory, "getScanner");
		return partialMockFactory;
	}
	
	private void accessNoLineFoundException(NoSuchElementException e) {
		String errorMessage = e.getMessage();
		assertNotNull(errorMessage);
		assertEquals("No line found", errorMessage);
	}
	
	private void givenMockByteArrayInputStreamScanner(String input) {
		InputStream in = FlowerShopTestFixture.givenByteArrayInputStream(input);
	    System.setIn(in);
	    mockScanner = new Scanner(System.in);
	}
}
