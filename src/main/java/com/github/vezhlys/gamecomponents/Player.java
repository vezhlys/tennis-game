package com.github.vezhlys.gamecomponents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.github.vezhlys.game.actionlisteners.PlayerHitListener;
import com.github.vezhlys.game.actionlisteners.PlayerMissListener;
import com.github.vezhlys.game.exceptions.GameException;
import com.github.vezhlys.game.exceptions.NoInputException;
import com.github.vezhlys.game.timer.InputTimer;

public final class Player extends GameComponent implements PlayerHitListener {
	private final String name;
	private final List<PlayerMissListener> missListeners;
	private final List<PlayerHitListener> hitListeners;

	public Player(final String name) {
		this.name = name;
		this.missListeners = Collections.synchronizedList(new ArrayList<>());
		this.hitListeners = Collections.synchronizedList(new ArrayList<>());
	}

	public void serve(final Player player) {
		System.out.println(name + " is serving the ball.");
		hitTheBall();
		player.onPlayerHit(this);
	}

	public void addMissListener(final PlayerMissListener missListener) {
		synchronized (missListeners) {
			missListeners.add(missListener);
		}
	}

	public void addHitListener(final PlayerHitListener hitListener) {
		synchronized (hitListeners) {
			hitListeners.add(hitListener);
		}
	}

	@Override
	public void draw() {
		System.out.println(name + " is ready.");
	}

	@Override
	public void onPlayerHit(final Player player) {
		try {
			waitForHumanPlayerResponse();
			hitTheBall();
		} catch (NoInputException e) {
			missTheBall();
		} catch (InterruptedException | ExecutionException e) {
			throw new GameException("Unexpected exception", e);
		}
	}

	private void waitForHumanPlayerResponse() throws NoInputException, InterruptedException, ExecutionException {
		System.out.println("Click enter to hit or wait 2 seconds to miss.");
		new InputTimer(2).readLine();
	}

	private void missTheBall() {
		System.out.println(name + " missed the ball.");
		missListeners.forEach(listener -> listener.onPlayerMiss(this));
	}

	private void hitTheBall() {
		System.out.println(name + " hit the ball.");
		hitListeners.forEach(listener -> listener.onPlayerHit(this));
	}

	@Override
	public String toString() {
		return name;
	}
}
