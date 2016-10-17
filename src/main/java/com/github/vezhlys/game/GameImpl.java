package com.github.vezhlys.game;

import com.github.vezhlys.gamecomponents.Player;

public final class GameImpl extends Game {
	public GameImpl() {
		super("Player1", "Player2");
	}

	@Override
	public void start() {
		addScoreBoardListeners();
		addPlayersListeners();
		draw();
		getPlayer1().serve(getPlayer2());
	}

	@Override
	protected Player initPlayer(final String playerName) {
		return new Player(playerName);
	}

	@Override
	protected void draw() {
		getPlayers().forEach(Player::draw);
		getScoreBoard().draw();
	}

	private void addPlayersListeners() {
		getPlayers().forEach(player -> {
			player.addMissListener(getScoreBoard());
			player.addMissListener((pl) -> this.getAnotherPlayer(pl).serve(pl));
			player.addHitListener(getAnotherPlayer(player));
		});
	}

	private void addScoreBoardListeners() {
		getScoreBoard().addGameWinListener((pl) -> {
			System.out.printf("\n%s won the game\n", pl);
			System.exit(0);
		});
	}
}
