package com.github.vezhlys.game.exceptions;

public class GameException extends RuntimeException {
	private static final long serialVersionUID = -2661679020769085926L;

	public GameException(final String message) {
		super(message);
	}

	public GameException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
