package com.github.vezhlys.game.validators;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.vezhlys.game.exceptions.GameException;
import com.github.vezhlys.gamecomponents.Player;

public class PlayersValidatorTest {
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void playerOneIsNullThrowsException() {
		exception.expect(GameException.class);
		exception.expectMessage("Player can't be null");
		new PlayersValidator().validate(null, new Player("Player1"));
	}

	@Test
	public void playerTwoIsNullThrowsException() {
		exception.expect(GameException.class);
		exception.expectMessage("Player can't be null");
		new PlayersValidator().validate(new Player("Player1"), null);
	}

	@Test
	public void playerAreEqualsThrowsException() {
		Player player = new Player("Player1");
		exception.expect(GameException.class);
		exception.expectMessage("Players should be different");
		new PlayersValidator().validate(player, player);
	}

	@Test
	public void nonNullDifferentPlayersAreValid() {
		new PlayersValidator().validate(new Player("Player1"), new Player("Player2"));
		Assert.assertTrue("No exception thrown", true);
	}

}
