package com.github.vezhlys.gamecomponents;

import org.junit.Assert;
import org.junit.Test;

import com.github.vezhlys.gamecomponents.enums.Score;

public class ScoreDecreaseTest {
	@Test
	public void doesntDecreaseIfOtherScoreIsWin() {
		Assert.assertEquals(Score.LOVE.decrease(Score.WIN), Score.LOVE);
		Assert.assertEquals(Score.FIFTEEN.decrease(Score.WIN), Score.FIFTEEN);
		Assert.assertEquals(Score.THIRTY.decrease(Score.WIN), Score.THIRTY);
		Assert.assertEquals(Score.FORTY.decrease(Score.WIN), Score.FORTY);
		Assert.assertEquals(Score.DEUCE.decrease(Score.WIN), Score.DEUCE);
		Assert.assertEquals(Score.ADVANTAGE.decrease(Score.WIN), Score.ADVANTAGE);
		Assert.assertEquals(Score.DISADVANTAGE.decrease(Score.WIN), Score.DISADVANTAGE);
		Assert.assertEquals(Score.WIN.decrease(Score.WIN), Score.WIN);
	}

	@Test
	public void decreaseFromLoveIsLove() {
		Assert.assertEquals(Score.LOVE.decrease(Score.LOVE), Score.LOVE);
		Assert.assertEquals(Score.LOVE.decrease(Score.FIFTEEN), Score.LOVE);
		Assert.assertEquals(Score.LOVE.decrease(Score.THIRTY), Score.LOVE);
		Assert.assertEquals(Score.LOVE.decrease(Score.FORTY), Score.LOVE);
	}

	@Test
	public void decreaseFromFifteenIsFifteen() {
		Assert.assertEquals(Score.FIFTEEN.decrease(Score.LOVE), Score.FIFTEEN);
		Assert.assertEquals(Score.FIFTEEN.decrease(Score.FIFTEEN), Score.FIFTEEN);
		Assert.assertEquals(Score.FIFTEEN.decrease(Score.THIRTY), Score.FIFTEEN);
		Assert.assertEquals(Score.FIFTEEN.decrease(Score.FORTY), Score.FIFTEEN);
	}

	@Test
	public void decreaseFromThirtyIsThirtyIfOtherScoreIsNotThirty() {
		Assert.assertEquals(Score.THIRTY.decrease(Score.LOVE), Score.THIRTY);
		Assert.assertEquals(Score.THIRTY.decrease(Score.FIFTEEN), Score.THIRTY);
	}

	@Test
	public void decreaseFromThirtyIsDisadvantageIfOtherScoreIsThirty() {
		Assert.assertEquals(Score.THIRTY.decrease(Score.THIRTY), Score.DISADVANTAGE);
	}

	@Test
	public void decreaseFromFortyIsFortyIfOtherScoreIsLessThirty() {
		Assert.assertEquals(Score.FORTY.decrease(Score.LOVE), Score.FORTY);
		Assert.assertEquals(Score.FORTY.decrease(Score.FIFTEEN), Score.FORTY);
	}

	@Test
	public void decreaseFromFortyIsDeuceIfOtherScoreIsThirty() {
		Assert.assertEquals(Score.FORTY.decrease(Score.THIRTY), Score.DEUCE);
	}

	@Test
	public void decreaseFromDeuceIsDisadvantage() {
		Assert.assertEquals(Score.DEUCE.decrease(Score.DEUCE), Score.DISADVANTAGE);
	}

	@Test
	public void decreaseFromAdvantageIsDeuce() {
		Assert.assertEquals(Score.ADVANTAGE.decrease(Score.DISADVANTAGE), Score.DEUCE);
	}

	@Test
	public void decreaseFromDisadvantageIsDisadvantage() {
		Assert.assertEquals(Score.DISADVANTAGE.decrease(Score.ADVANTAGE), Score.DISADVANTAGE);
	}
}
