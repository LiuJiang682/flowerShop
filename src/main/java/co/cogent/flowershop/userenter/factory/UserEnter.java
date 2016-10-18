package co.cogent.flowershop.userenter.factory;

import java.util.Optional;

import co.cogent.flowershop.userenter.UserEntry;

public interface UserEnter {

	String SPACE = " ";

	Optional<UserEntry> getUserInput();
}
