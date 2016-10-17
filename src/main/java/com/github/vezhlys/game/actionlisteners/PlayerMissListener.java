package com.github.vezhlys.game.actionlisteners;

import com.github.vezhlys.gamecomponents.Player;

@FunctionalInterface
public interface PlayerMissListener {
	void onPlayerMiss(Player player);
}
