package com.github.vezhlys.game.validators;

import java.util.Optional;

import com.github.vezhlys.game.exceptions.GameException;
import com.github.vezhlys.gamecomponents.Player;

public class PlayersValidator {
	public void validate(final Player player1, final Player player2) {
		validatePlayerIsNotNull(player1);
		validatePlayerIsNotNull(player2);
		validatePlayersAreNotEqual(player1, player2);
	}

	private void validatePlayersAreNotEqual(final Player player1, final Player player2) {
		if (player1.equals(player2)) {
			throw new GameException("Players should be different");
		}
	}

	private Player validatePlayerIsNotNull(final Player player) {
		return Optional.ofNullable(player).orElseThrow(() -> new GameException("Player can't be null"));
	}
}
