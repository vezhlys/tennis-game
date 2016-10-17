package com.github.vezhlys.game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.github.vezhlys.gamecomponents.Player;
import com.github.vezhlys.gamecomponents.ScoreBoard;

public abstract class Game {
	private final List<Player> players;
	private final ScoreBoard scoreBoard;

	public Game(final String playerName1, final String playerName2) {
		players = initPlayers(getName(playerName1, 1), getName(playerName2, 2));
		scoreBoard = new ScoreBoard(players.get(0), players.get(1));
	}

	abstract public void start();

	abstract protected Player initPlayer(final String playerName);

	abstract protected void draw();

	protected final List<Player> getPlayers() {
		return players;
	}

	protected final Player getPlayer1() {
		return players.get(0);
	}

	protected final Player getPlayer2() {
		return players.get(1);
	}

	protected final ScoreBoard getScoreBoard() {
		return scoreBoard;
	}

	protected final Player getAnotherPlayer(final Player player) {
		return players.stream().filter(otherPlayer(player)).findFirst().get();
	}

	private List<Player> initPlayers(final String playerName1, final String playerName2) {
		return Collections.unmodifiableList(Arrays.asList(initPlayer(playerName1), initPlayer(playerName2)));
	}

	private Predicate<Player> otherPlayer(final Player player) {
		return pl -> !pl.equals(player);
	}

	private String getName(final String playerName, final int index) {
		return Optional.ofNullable(playerName).map(name -> {
			return name.equals("") ? getDefaultName(index) : name;
		}).orElse(getDefaultName(index));
	}

	private String getDefaultName(final int index) {
		return "Player" + index;
	}
}
