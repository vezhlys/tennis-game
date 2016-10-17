package com.github.vezhlys.gamecomponents;

import org.junit.Assert;
import org.junit.Test;

import com.github.vezhlys.gamecomponents.enums.Score;

public class ScoreIncreaseTest {

	@Test
	public void doesntIncreaseIfOtherScoreIsWin() {
		Assert.assertEquals(Score.LOVE.increase(Score.WIN), Score.LOVE);
		Assert.assertEquals(Score.FIFTEEN.increase(Score.WIN), Score.FIFTEEN);
		Assert.assertEquals(Score.THIRTY.increase(Score.WIN), Score.THIRTY);
		Assert.assertEquals(Score.FORTY.increase(Score.WIN), Score.FORTY);
		Assert.assertEquals(Score.DEUCE.increase(Score.WIN), Score.DEUCE);
		Assert.assertEquals(Score.ADVANTAGE.increase(Score.WIN), Score.ADVANTAGE);
		Assert.assertEquals(Score.DISADVANTAGE.increase(Score.WIN), Score.DISADVANTAGE);
		Assert.assertEquals(Score.WIN.increase(Score.WIN), Score.WIN);
	}

	@Test
	public void increaseFromLoveIsFifteen() {
		Assert.assertEquals(Score.LOVE.increase(Score.LOVE), Score.FIFTEEN);
		Assert.assertEquals(Score.LOVE.increase(Score.FIFTEEN), Score.FIFTEEN);
		Assert.assertEquals(Score.LOVE.increase(Score.THIRTY), Score.FIFTEEN);
		Assert.assertEquals(Score.LOVE.increase(Score.FORTY), Score.FIFTEEN);
	}

	@Test
	public void increaseFromFifteenIsThirty() {
		Assert.assertEquals(Score.FIFTEEN.increase(Score.LOVE), Score.THIRTY);
		Assert.assertEquals(Score.FIFTEEN.increase(Score.FIFTEEN), Score.THIRTY);
		Assert.assertEquals(Score.FIFTEEN.increase(Score.THIRTY), Score.THIRTY);
		Assert.assertEquals(Score.FIFTEEN.increase(Score.FORTY), Score.THIRTY);
	}

	@Test
	public void increaseFromThirtyIsFortyIfOtherScoreIsLessThanThirty() {
		Assert.assertEquals(Score.THIRTY.increase(Score.LOVE), Score.FORTY);
		Assert.assertEquals(Score.THIRTY.increase(Score.FIFTEEN), Score.FORTY);
		Assert.assertEquals(Score.THIRTY.increase(Score.THIRTY), Score.FORTY);
	}

	@Test
	public void increaseFromThirtyIsDeuceIfOtherScoreIsForty() {
		Assert.assertEquals(Score.THIRTY.increase(Score.FORTY), Score.DEUCE);
	}

	@Test
	public void increaseFromFortyIsWinIfOtherScoreIsLessThanForty() {
		Assert.assertEquals(Score.FORTY.increase(Score.LOVE), Score.WIN);
		Assert.assertEquals(Score.FORTY.increase(Score.FIFTEEN), Score.WIN);
		Assert.assertEquals(Score.FORTY.increase(Score.THIRTY), Score.WIN);
	}

	@Test
	public void increaseFromFortyIsAdvantageIfOtherScoreIsForty() {
		Assert.assertEquals(Score.DEUCE.increase(Score.DEUCE), Score.ADVANTAGE);
	}

	@Test
	public void increaseFromDisadvantageIsDeuce() {
		Assert.assertEquals(Score.DISADVANTAGE.increase(Score.ADVANTAGE), Score.DEUCE);
	}

	@Test
	public void increaseFromAdvantageIsWin() {
		Assert.assertEquals(Score.ADVANTAGE.increase(Score.DISADVANTAGE), Score.WIN);
	}
}
