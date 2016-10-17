package com.github.vezhlys.gamecomponents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.vezhlys.game.actionlisteners.GameWinListener;
import com.github.vezhlys.game.actionlisteners.PlayerMissListener;
import com.github.vezhlys.game.validators.PlayersValidator;
import com.github.vezhlys.gamecomponents.enums.Score;

public final class ScoreBoard extends GameComponent implements PlayerMissListener {
	private static final String GAME_SCORE_TEXT = "Game score is ";
	private final Map<Player, Score> scores = Collections.synchronizedMap(new HashMap<>());
	private final List<GameWinListener> gameWinListeners;

	public ScoreBoard(final Player player1, final Player player2) {
		new PlayersValidator().validate(player1, player2);
		scores.put(player1, Score.LOVE);
		scores.put(player2, Score.LOVE);
		gameWinListeners = Collections.synchronizedList(new ArrayList<>());
	}

	public void addGameWinListener(final GameWinListener gameWinListener) {
		synchronized (gameWinListeners) {
			gameWinListeners.add(gameWinListener);
		}
	}

	@Override
	public void draw() {
		System.out.printf("\n%s", GAME_SCORE_TEXT);
		scores.keySet().stream().forEach(this::drawPlayerScore);
		System.out.printf("\n\n");
	}

	@Override
	public void onPlayerMiss(final Player player) {
		final Player otherPlayer = scores.keySet().stream().filter(pl -> !pl.equals(player)).findFirst().get();
		final Score playerScore = scores.get(player);
		final Score otherPlayerScore = scores.get(otherPlayer);
		synchronized (scores) {
			scores.replace(otherPlayer, otherPlayerScore.increase(playerScore));
			scores.replace(player, playerScore.decrease(otherPlayerScore));
		}
		if (scores.containsValue(Score.WIN)) {
			gameWinListeners.stream().forEach(listener -> listener.onGameWin(otherPlayer));
		}
		draw();
	}

	private void drawPlayerScore(final Player player) {
		System.out.printf("%s %s ", player, scores.get(player).displayScore());
	}

}
