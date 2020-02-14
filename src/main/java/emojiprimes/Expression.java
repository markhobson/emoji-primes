package emojiprimes;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

public class Expression
{
	private final List<Symbol> symbols;
	
	public Expression(Symbol... symbols)
	{
		this(asList(symbols));
	}
	
	public Expression(Expression expression)
	{
		this(expression.symbols);
	}
	
	private Expression(List<Symbol> symbols)
	{
		this.symbols = new ArrayList<>(symbols);
	}
	
	public Expression add(Symbol... symbols)
	{
		this.symbols.addAll(asList(symbols));
		
		return this;
	}
	
	public int size()
	{
		return symbols.size();
	}
	
	// See: https://en.wikipedia.org/wiki/Operator-precedence_parser
	public int evaluate()
	{
		PeekingIterator<Symbol> iterator = Iterators.peekingIterator(symbols.iterator());
		Operand operand = iterator.next().asOperand();
		
		return evaluate(iterator, operand.getValue(), 0);
	}
	
	private static int evaluate(PeekingIterator<Symbol> iterator, int lhs, int minPrecedence)
	{
		while (iterator.hasNext() && iterator.peek().isOperator(minPrecedence))
		{
			Operator operator = iterator.next().asOperator();
			int rhs = iterator.next().asOperand().getValue();
			
			while (iterator.hasNext() && iterator.peek().isOperator(operator.getPrecedence() + 1))
			{
				Operator peekOperator = iterator.peek().asOperator();
				rhs = evaluate(iterator, rhs, peekOperator.getPrecedence());
			}
			
			lhs = operator.evaluate(lhs, rhs);
		}
		
		return lhs;
	}
	
	@Override
	public String toString()
	{
		return symbols.stream().map(Object::toString).collect(joining(" "));
	}
}
