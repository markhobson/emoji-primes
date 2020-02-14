package org.hobsoft.emojiprimes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("Finding emoji primes...");
		
		Map<Integer, Expression> results = new Emojifier().emojify();
		
		String filename = "emoji-primes.md";
		try (PrintWriter writer = new PrintWriter(filename, "UTF-8"))
		{
			print(results, writer);
		}
		System.out.format("Saved results to %s%n", filename);
	}
	
	private static void print(Map<Integer, Expression> results, PrintWriter writer)
	{
		writer.println("# Emoji Primes");
		writer.println();
		writer.println("```");
		
		Integer previous = null;
		
		for (Integer number : results.keySet())
		{
			while (previous != null && number > previous + 1)
			{
				previous++;
				writer.format("%5d = emoji prime 🎉%n", previous);
			}
			
			writer.format("%5d = %s%n", number, results.get(number));
			
			previous = number;
		}
		
		writer.println("```");
	}
}
