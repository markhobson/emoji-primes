package org.hobsoft.emojiprimes;

import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;

public class Emojifier
{
	private static final EnumSet<Operand> NUMBERS = EnumSet.allOf(Operand.class);

	private static final EnumSet<Operator> OPERATORS = EnumSet.allOf(Operator.class);
	
	public Map<Integer, Expression> emojify()
	{
		Map<Integer, Expression> results = new TreeMap<>();
		
		for (Operand number : NUMBERS)
		{
			emojify(results, new Expression(number), remove(NUMBERS, number), OPERATORS);
		}
		
		return results;
	}
	
	public void emojify(Map<Integer, Expression> results, Expression expression, EnumSet<Operand> numbers,
		EnumSet<Operator> operators)
	{
		evaluate(results, expression);

		for (Operator operator : operators)
		{
			for (Operand number : numbers)
			{
				Expression next = new Expression(expression).add(operator, number);
				
				emojify(results, next, remove(numbers, number), remove(operators, operator));
			}
		}
	}
	
	private static void evaluate(Map<Integer, Expression> results, Expression expression)
	{
		try
		{
			int value = expression.evaluate();
			Expression previous = results.get(value);
			
			if (previous == null || expression.size() < previous.size())
			{
				results.put(value, expression);
			}
		}
		catch (EvaluationException exception)
		{
			// ignore invalid expressions
		}
	}
	
	private static <E extends Enum<E>> EnumSet<E> remove(EnumSet<E> set, E element)
	{
		EnumSet<E> result = EnumSet.copyOf(set);
		result.remove(element);
		return result;
	}
}
