package com.github.vezhlys.gamecomponents;

import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import com.github.vezhlys.game.actionlisteners.GameWinListener;

public class ScoreBoardTest {
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests();

	private class StubWinListener implements GameWinListener {
		private boolean active;

		public boolean isActive() {
			return active;
		}

		@Override
		public void onGameWin(final Player player) {
			active = true;
		}
	}

	@Test
	public void drawOutputsInitialScore() {
		systemOutRule.clearLog();
		ScoreBoard scoreBoard = new ScoreBoard(new Player("Player1"), new Player("Player1"));
		scoreBoard.draw();
		Assert.assertEquals(systemOutRule.getLog(), "\nGame score is Player1 love Player1 love \n\n");
	}

	@Test
	public void onPlayerMissChangeTheScore() {
		systemOutRule.clearLog();
		Player player1 = new Player("Player1");
		ScoreBoard scoreBoard = new ScoreBoard(player1, new Player("Player2"));
		scoreBoard.onPlayerMiss(player1);
		Assert.assertTrue(systemOutRule.getLog().contains("fifteen"));
	}

	@Test
	public void onWinListenerIsNotified() {
		systemOutRule.clearLog();
		Player player1 = new Player("Player1");
		ScoreBoard scoreBoard = new ScoreBoard(player1, new Player("Player2"));
		StubWinListener winListener = new StubWinListener();
		scoreBoard.addGameWinListener(winListener);
		IntStream.range(0, 4).forEach(i -> scoreBoard.onPlayerMiss(player1));
		Assert.assertTrue(winListener.isActive());

	}
}
