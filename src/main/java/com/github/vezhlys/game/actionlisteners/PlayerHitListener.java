package com.github.vezhlys.game.actionlisteners;

import com.github.vezhlys.gamecomponents.Player;

@FunctionalInterface
public interface PlayerHitListener {
	void onPlayerHit(Player player);
}
