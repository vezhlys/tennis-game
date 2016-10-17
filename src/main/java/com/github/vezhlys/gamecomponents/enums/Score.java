package com.github.vezhlys.gamecomponents.enums;

public enum Score {
	LOVE("love"), FIFTEEN("fifteen"), THIRTY("thirty"), FORTY("forty"), WIN("win"), DISADVANTAGE("-"), DEUCE(
			"deuce"), ADVANTAGE("advantage");
	private String displayText;

	private Score(final String displayText) {
		this.displayText = displayText;
	}

	public String displayScore() {
		return displayText;
	}

	public Score increase(final Score originalOtherPlayerScore) {
		if (originalOtherPlayerScore.equals(WIN) || WIN.equals(this)) {
			return this;
		}
		switch (this) {
		case ADVANTAGE:
			return WIN;
		case THIRTY:
		case FORTY:
			if (originalOtherPlayerScore.compareTo(Score.THIRTY) > 0) {
				return this.equals(originalOtherPlayerScore) ? ADVANTAGE : DEUCE;
			}
		default:
			return values()[(ordinal() + 1) % values().length];
		}
	}

	public Score decrease(final Score originalOtherPlayerScore) {
		if (originalOtherPlayerScore.equals(WIN)) {
			return this;
		}
		switch (this) {
		case ADVANTAGE:
			return DEUCE;
		case DEUCE:
			return DISADVANTAGE;
		case THIRTY:
			return originalOtherPlayerScore.equals(this) ? DISADVANTAGE : this;
		default:
			return (FORTY.equals(this) && originalOtherPlayerScore.equals(THIRTY)) ? DEUCE : this;
		}
	}
}
