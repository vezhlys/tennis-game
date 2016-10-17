package com.github.vezhlys.game;

import com.github.vezhlys.game.exceptions.GameException;

public class GameApp {
	public static void main(final String args[]) {
		try {
			new GameImpl().start();
		} catch (GameException e) {
			System.out.println(e.getMessage());
		}
	}
}
