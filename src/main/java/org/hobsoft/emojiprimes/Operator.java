package org.hobsoft.emojiprimes;

public enum Operator implements Symbol
{
	PLUS("➕", 1)
	{
		@Override
		public int evaluate(int x, int y)
		{
			return x + y;
		}
	},
	
	MINUS("➖", 1)
	{
		@Override
		public int evaluate(int x, int y)
		{
			return x - y;
		}
	},
	
	MULTIPLY("✖️", 2)
	{
		@Override
		public int evaluate(int x, int y)
		{
			return x * y;
		}
	},

	DIVIDE("➗", 2)
	{
		@Override
		public int evaluate(int x, int y)
		{
			if (y == 0)
			{
				throw new EvaluationException("Division by zero");
			}
			
			int result = x / y;
			
			if (result * y != x)
			{
				throw new EvaluationException("Non-integer division");
			}
			
			return result;
		}
	};
	
	private final String symbol;
	
	private final int precedence;
	
	Operator(String symbol, int precedence)
	{
		this.symbol = symbol;
		this.precedence = precedence;
	}
	
	public int getPrecedence()
	{
		return precedence;
	}
	
	public abstract int evaluate(int x, int y);
	
	@Override
	public String toString()
	{
		return symbol;
	}
}
