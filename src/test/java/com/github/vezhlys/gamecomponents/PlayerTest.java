package com.github.vezhlys.gamecomponents;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import com.github.vezhlys.game.actionlisteners.PlayerHitListener;
import com.github.vezhlys.game.actionlisteners.PlayerMissListener;

public class PlayerTest {
	private static final String PLAYER2 = "Player2";
	private static final String PLAYER = "Player";
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests();

	private class StubHitListener implements PlayerHitListener, PlayerMissListener {
		private boolean hitActive;
		private boolean missActive;

		public boolean isHitActive() {
			return hitActive;
		}

		public boolean isMissActive() {
			return missActive;
		}

		@Override
		public void onPlayerHit(final Player player) {
			hitActive = true;
		}

		@Override
		public void onPlayerMiss(final Player player) {
			missActive = true;
		}
	}

	@Test
	public void playerToStringEqualsName() {
		final String playerName = PLAYER;
		Assert.assertTrue(new Player(playerName).toString().equals(playerName));
	}

	@Test
	public void playerDrawOutputsThatHeIsReady() {
		systemOutRule.clearLog();
		new Player(PLAYER).draw();
		Assert.assertEquals("Player is ready.\n", systemOutRule.getLog());
	}

	@Test
	public void listenersAreNotified() {
		final Player player = new Player(PLAYER);
		final Player player2 = new Player(PLAYER2);
		StubHitListener stubListener = new StubHitListener();
		player.addHitListener(stubListener);
		player2.addMissListener(stubListener);
		player.serve(player2);
		Assert.assertTrue(stubListener.isHitActive());
		Assert.assertTrue(stubListener.isMissActive());
	}

}
