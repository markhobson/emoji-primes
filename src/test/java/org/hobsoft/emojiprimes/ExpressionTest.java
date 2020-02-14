package org.hobsoft.emojiprimes;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hobsoft.emojiprimes.Operand.FOUR;
import static org.hobsoft.emojiprimes.Operand.ONE;
import static org.hobsoft.emojiprimes.Operand.THREE;
import static org.hobsoft.emojiprimes.Operand.TWO;
import static org.hobsoft.emojiprimes.Operator.MULTIPLY;
import static org.hobsoft.emojiprimes.Operator.PLUS;

public class ExpressionTest
{
	@Test
	public void canEvaluateOperand()
	{
		assertThat(new Expression(ONE).evaluate(), is(1));
	}
	
	@Test
	public void canEvaluateOperator()
	{
		assertThat(new Expression(ONE, PLUS, TWO).evaluate(), is(3));
	}
	
	@Test
	public void canEvaluateOperators()
	{
		assertThat(new Expression(ONE, PLUS, TWO, PLUS, THREE).evaluate(), is(6));
	}
	
	@Test
	public void canEvaluateOperatorsWithPrecedence()
	{
		assertThat(new Expression(ONE, PLUS, TWO, MULTIPLY, THREE).evaluate(), is(7));
	}
	
	@Test
	public void canEvaluateOperatorsWithMixedPrecedence()
	{
		assertThat(new Expression(ONE, PLUS, TWO, MULTIPLY, THREE, PLUS, FOUR).evaluate(), is(11));
	}
}
