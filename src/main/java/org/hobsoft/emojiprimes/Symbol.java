package org.hobsoft.emojiprimes;

public interface Symbol
{
	default Operand asOperand()
	{
		return (Operand) this;
	}
	
	default Operator asOperator()
	{
		return (Operator) this;
	}
	
	default boolean isOperator(int minPrecedence)
	{
		return (this instanceof Operator)
			&& ((Operator) this).getPrecedence() >= minPrecedence;
	}
}