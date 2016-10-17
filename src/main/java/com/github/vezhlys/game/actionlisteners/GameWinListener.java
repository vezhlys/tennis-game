package com.github.vezhlys.game.actionlisteners;

import com.github.vezhlys.gamecomponents.Player;

@FunctionalInterface
public interface GameWinListener {
	void onGameWin(Player player);
}
